package site.sugarnest.backend.service.cart;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.sugarnest.backend.dto.request.CartItemRequest;
import site.sugarnest.backend.dto.response.CartItemResponse;
import site.sugarnest.backend.entities.CartEntity;
import site.sugarnest.backend.entities.CartItemEntity;
import site.sugarnest.backend.entities.ProductEntity;
import site.sugarnest.backend.entities.AccountEntity;
import site.sugarnest.backend.mapper.ICartMapper;
import site.sugarnest.backend.reponsitoties.IAccountRepository;
import site.sugarnest.backend.reponsitoties.ICartItemRepository;
import site.sugarnest.backend.reponsitoties.ICartRepository;
import site.sugarnest.backend.reponsitoties.IProductRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final ICartRepository cartRepository;

    private final IProductRepository productRepository;

    private final ICartItemRepository cartItemRepository;

    private final IAccountRepository accountRepository;

    private final ICartMapper cartMapper;

    public CartService(ICartRepository cartRepository, IProductRepository productRepository, ICartItemRepository cartItemRepository, IAccountRepository accountRepository, ICartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
        this.accountRepository = accountRepository;
        this.cartMapper = cartMapper;
    }

    @Transactional
    public CartItemResponse addItemToCart(CartItemRequest cartItemRequest) {
        AccountEntity account = accountRepository.findById(cartItemRequest.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        CartEntity cart = cartRepository.findByAccountEntity(account).orElse(null);

        if (cart == null) {
            cart = new CartEntity();
            cart.setAccountEntity(account);
            cart.setCreatedAt(new Date());
            cart.setUpdatedAt(new Date());
            cart.setStatus("active");
            cartRepository.save(cart);
        }

        ProductEntity product = productRepository.findById(cartItemRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItemEntity> existingCartItemOpt = cart.getCartItems().stream()
                .filter(item -> item.getProductEntity().equals(product) &&
                        item.getProductSize().equals(cartItemRequest.getProductSize()) &&
                        item.getProductColor().equals(cartItemRequest.getProductColor()))
                .findFirst();

        CartItemEntity cartItem;

        if (existingCartItemOpt.isPresent()) {
            cartItem = existingCartItemOpt.get();
            int newQuantity = cartItem.getQuantity() + cartItemRequest.getQuantity();
            cartItem.setQuantity(newQuantity);
            cartItem.setPrice(product.getProductPriceEntity().getDiscountPrice() * newQuantity);
        } else {
            cartItem = new CartItemEntity();
            cartItem.setCartEntity(cart);
            cartItem.setProductEntity(product);
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setProductSize(cartItemRequest.getProductSize());
            cartItem.setProductColor(cartItemRequest.getProductColor());
            cartItem.setPrice(product.getProductPriceEntity().getDiscountPrice() * cartItemRequest.getQuantity());
            cart.getCartItems().add(cartItem);
        }

        cart.setUpdatedAt(new Date());
        if (cart.getTotalPrice() == null) {
            cart.setTotalPrice(0.0);
        }

        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(CartItemEntity::getPrice)
                .sum();
        cart.setTotalPrice(totalPrice);

        cartRepository.save(cart);

        CartItemResponse cartItemResponse = cartMapper.mapToCartItemDto(cartItem);
        cartItemResponse.setAccountId(cartItemRequest.getAccountId());
        cartItemResponse.setProductId(cartItemRequest.getProductId());
        cartItemResponse.setCartId(cart.getId());
        return cartItemResponse;
    }


    @Transactional
    public void removeItemFromCart(Integer cartItemId) {
        var context = SecurityContextHolder.getContext();
        String accountName = context.getAuthentication().getName();
        AccountEntity account = accountRepository.findByAccountName(accountName).orElseThrow(() -> new RuntimeException("Account not found"));
        CartEntity cart = cartRepository.findByAccountEntity(account).orElseThrow(() -> new RuntimeException("Cart not found"));
        CartItemEntity cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("CartItem not found"));
        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        cart.setUpdatedAt(new Date());
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(CartItemEntity::getPrice)
                .sum();
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
    }

    @Transactional
    public void increaseQuantity(Integer cartItemId) {
        var context = SecurityContextHolder.getContext();
        String accountName = context.getAuthentication().getName();
        AccountEntity account = accountRepository.findByAccountName(accountName).orElseThrow(() -> new RuntimeException("Account not found"));
        CartEntity cart = cartRepository.findByAccountEntity(account).orElseThrow(() -> new RuntimeException("Cart not found"));
        CartItemEntity cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("CartItem not found"));

        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItem.setPrice(cartItem.getProductEntity().getProductPriceEntity().getDiscountPrice() * cartItem.getQuantity());

        cartItemRepository.save(cartItem);
        cart.setUpdatedAt(new Date());
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(CartItemEntity::getPrice)
                .sum();
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
    }

    @Transactional
    public void decreaseQuantity(Integer cartItemId) {
        var context = SecurityContextHolder.getContext();
        String accountName = context.getAuthentication().getName();
        AccountEntity account = accountRepository.findByAccountName(accountName).orElseThrow(() -> new RuntimeException("Account not found"));
        CartEntity cart = cartRepository.findByAccountEntity(account).orElseThrow(() -> new RuntimeException("Cart not found"));
        CartItemEntity cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("CartItem not found"));

        int newQuantity = cartItem.getQuantity() - 1;
        if (newQuantity <= 0) {
            cart.getCartItems().remove(cartItem);
            cartItemRepository.delete(cartItem);
        } else {
            cartItem.setQuantity(newQuantity);
            cartItem.setPrice(cartItem.getProductEntity().getProductPriceEntity().getDiscountPrice() * cartItem.getQuantity());
            cartItemRepository.save(cartItem);
        }

        cart.setUpdatedAt(new Date());
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(CartItemEntity::getPrice)
                .sum();
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
    }

    public CartEntity getCartByAccountId(Long accountId) {
        AccountEntity account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        return cartRepository.findByAccountEntity(account).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public List<CartItemEntity> getCartItemsByCartId(Integer cartId) {
        return cartItemRepository.findByCartEntityId(cartId);
    }

    public Integer getTotalItemsInCart(Long accountId) {
        AccountEntity account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        CartEntity cart = cartRepository.findByAccountEntity(account).orElseThrow(() -> new RuntimeException("Cart not found"));
        List<CartItemEntity> cartItems = getCartItemsByCartId(cart.getId());
        return cartItems.stream().mapToInt(CartItemEntity::getQuantity).sum();
    }

    public List<CartEntity> getAllCarts() {
        return cartRepository.findAll();
    }

    public CartEntity getMyCart() {
        var context = SecurityContextHolder.getContext();
        String accountName = context.getAuthentication().getName();
        AccountEntity account = accountRepository.findByAccountName(accountName).orElseThrow(() -> new RuntimeException("Account not found"));
        return cartRepository.findByAccountEntity(account).orElseThrow(() -> new RuntimeException("Cart not found"));
    }
}
