package site.sugarnest.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.sugarnest.backend.dto.dto.ApiResponse;
import site.sugarnest.backend.dto.request.CartItemRequest;
import site.sugarnest.backend.dto.response.CartItemResponse;
import site.sugarnest.backend.entities.CartEntity;
import site.sugarnest.backend.entities.CartItemEntity;
import site.sugarnest.backend.service.cart.CartService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping({"/", "/all", ""})
    public ApiResponse<List<CartEntity>> getAllCarts() {
        return ApiResponse.<List<CartEntity>>builder()
                .code(200)
                .result(cartService.getAllCarts())
                .build();
    }

    @PostMapping("/add-item")
    public ApiResponse<CartItemResponse> addItemToCart(@RequestBody CartItemRequest cartItemDto) {
        return ApiResponse.<CartItemResponse>builder()
                .code(200)
                .result(cartService.addItemToCart(cartItemDto))
                .build();
    }

    @DeleteMapping("/remove-item/{cartItemId}")
    public ApiResponse<Void> removeItemFromCart(@PathVariable("cartItemId") Integer cartItemId) {
        cartService.removeItemFromCart(cartItemId);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Item removed from cart!")
                .build();
    }

    @PutMapping("/increase-quantity/{cartItemId}")
    public ApiResponse<Void> increaseQuantity(@PathVariable("cartItemId") Integer cartItemId) {
        cartService.increaseQuantity(cartItemId);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Quantity increased!")
                .build();
    }

        @PutMapping("/decrease-quantity/{cartItemId}")
    public ApiResponse<Void> decreaseQuantity(@PathVariable("cartItemId") Integer cartItemId) {
        cartService.decreaseQuantity(cartItemId);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Quantity decreased!")
                .build();
    }

    @GetMapping("/{accountId}")
    public ApiResponse<CartEntity> getCart(@PathVariable Long accountId) {
        return ApiResponse.<CartEntity>builder()
                .code(200)
                .result(cartService.getCartByAccountId(accountId))
                .build();
    }
    @GetMapping("/cart-items/{cartId}")
    public ApiResponse<List<CartItemEntity>> getCartItemsByCartId(@PathVariable Integer cartId) {
        return ApiResponse.<List<CartItemEntity>>builder()
                .code(200)
                .result(cartService.getCartItemsByCartId(cartId))
                .build();
    }
    @GetMapping("/total-items/{accountId}")
    public Double getTotalItemsInCart(@PathVariable Long accountId) {
        return ApiResponse.<Double>builder()
                .code(200)
                .result(Double.valueOf(cartService.getTotalItemsInCart(accountId)))
                .build().getResult();
    }
    @GetMapping("/my-cart")
    public ApiResponse<CartEntity> getMyCart() {
        return ApiResponse.<CartEntity>builder()
                .code(200)
                .result(cartService.getMyCart())
                .build();
    }
}
