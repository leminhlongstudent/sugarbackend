package site.sugarnest.backend.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.sugarnest.backend.dto.request.OrderRequest;
import site.sugarnest.backend.dto.response.OrderResponse;
import site.sugarnest.backend.entities.*;
import site.sugarnest.backend.mapper.IOrderMapper;
import site.sugarnest.backend.reponsitoties.ICartItemRepository;
import site.sugarnest.backend.reponsitoties.ICartRepository;
import site.sugarnest.backend.reponsitoties.IOrderDetailRepository;
import site.sugarnest.backend.reponsitoties.IOrderRepository;
import site.sugarnest.backend.service.account.IAccountService;
import site.sugarnest.backend.service.cart.CartService;

import java.util.*;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IAccountService iaccountService;

    @Autowired
    private ICartRepository iCartRepository;

    @Autowired
    private ICartItemRepository iCartItemRepository;

    @Autowired
    private IOrderDetailRepository iOrderDetailRepository;

    @Autowired
    private IOrderRepository iorderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ICartRepository cartRepository;


    @Autowired
    private IOrderMapper orderMapper;

    @Override
    public OrderResponse saveOrder(OrderRequest orderRequest) {
        CartEntity cart = cartService.getMyCart();
        List<CartItemEntity> cartItems = cart.getCartItems();
        OrderEntity order = getOrderEntity(orderRequest, cart);
        iorderRepository.save(order);

        for (CartItemEntity cartItem : cartItems) {
            OrderDetailEntity orderDetail = new OrderDetailEntity();
            orderDetail.setPrice(cartItem.getPrice());
            orderDetail.setProductColor(cartItem.getProductColor());
            orderDetail.setProductSize(cartItem.getProductSize());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setProductEntity(cartItem.getProductEntity());
            orderDetail.setOrderEntity(order);
            order.getOrderItems().add(orderDetail);
            iOrderDetailRepository.save(orderDetail);
            iCartItemRepository.delete(cartItem);
        }
        cart.getCartItems().removeAll(cartItems);
        cart.setUpdatedAt(new Date());
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);
        return orderMapper.toOrderEntity(order);
    }

    private OrderEntity getOrderEntity(OrderRequest orderRequest, CartEntity cart) {
        OrderEntity order = new OrderEntity();

        order.setAddress(orderRequest.getAddress());
        order.setDeliveryAt(orderRequest.getDeliveryAt());
        order.setNote(orderRequest.getNote());
        order.setSale(orderRequest.getSale());
        order.setCreateAt(new Date());
        order.setUpdateAt(new Date());
        order.setFree_ship(40000.0);
        order.setAccountEntity(cart.getAccountEntity());
        order.setStatus("Chờ xác nhận");
        order.setTotalPrice(cart.getTotalPrice());
        return order;
    }

    @Override
    public List<OrderResponse> getOrders() {
        AccountEntity accountEntity = iaccountService.getAccount();
        List<OrderEntity> orderEntitieslist = iorderRepository.findByAccountEntity(accountEntity);
        List<OrderResponse> orderResponseListlist = new ArrayList<>();
        for (OrderEntity order : orderEntitieslist) {
            OrderResponse orderResponse = orderMapper.toOrderEntity(order);
            orderResponseListlist.add(orderResponse);
        }
        return orderResponseListlist;
    }

    @Override
    public List<OrderResponse> getOrdersByAdmin() {
        List<OrderEntity> orderEntitieslist = iorderRepository.findAll();
        List<OrderResponse> orderResponseListlist = new ArrayList<>();
        for (OrderEntity order : orderEntitieslist) {
            OrderResponse orderResponse = orderMapper.toOrderEntity(order);
            orderResponseListlist.add(orderResponse);
        }
        return orderResponseListlist;
    }

    @Override
    public void updateOrderStatus(Integer orderId, String status) {
        Optional<OrderEntity> orderEntity = iorderRepository.findById(orderId);
        if (orderEntity.isPresent()) {
            OrderEntity order = orderEntity.get();
            order.setStatus(status);
            iorderRepository.save(order);
        }
    }
}
