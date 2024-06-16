package site.sugarnest.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import site.sugarnest.backend.dto.request.OrderRequest;
import site.sugarnest.backend.dto.response.ApiResponse;
import site.sugarnest.backend.dto.response.OrderResponse;
import site.sugarnest.backend.service.order.IOrderService;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService iorderService;

    @PostMapping
    public ApiResponse<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = iorderService.saveOrder(orderRequest);
        return ApiResponse.<OrderResponse>builder()
                .code(200)
                .result(orderResponse)
                .build();
    }

    @GetMapping("my-orders")
    public ApiResponse<List<OrderResponse>> getMyOrders() {
        List<OrderResponse> orderResponseList = iorderService.getOrders();
        return ApiResponse.<List<OrderResponse>>builder()
                .code(200)
                .result(orderResponseList)
                .build();
    }

    @GetMapping("admin-orders")
    @PreAuthorize("hasAuthority('ORDERS_GET')")
    public ApiResponse<List<OrderResponse>> getAdminOrders() {
        List<OrderResponse> orderResponseList = iorderService.getOrdersByAdmin();
        return ApiResponse.<List<OrderResponse>>builder()
                .code(200)
                .result(orderResponseList)
                .build();
    }

    @PutMapping("{orderId}/status")
    @PreAuthorize("hasAuthority('ORDERS_PUT')")
    public ApiResponse<String> updateOrderStatus(@PathVariable Integer orderId, @RequestBody String status) {
        iorderService.updateOrderStatus(orderId, status);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Success")
                .build();
    }

}
