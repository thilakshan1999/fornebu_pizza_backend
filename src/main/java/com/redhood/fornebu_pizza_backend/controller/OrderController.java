package com.redhood.fornebu_pizza_backend.controller;

import com.redhood.fornebu_pizza_backend.dto.OrderRequestDTO;
import com.redhood.fornebu_pizza_backend.entity.Order;
import com.redhood.fornebu_pizza_backend.resources.OrderSimpleResource;
import com.redhood.fornebu_pizza_backend.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user/{uid}")
    public List<OrderSimpleResource> getOrdersByUserId(@PathVariable String uid) {
        return orderService.getSimpleOrdersByUserId(uid);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderSimpleResource>> getOrdersByStatus(@PathVariable String status) {
        List<OrderSimpleResource> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        boolean updated = orderService.updateOrderStatus(orderId, status);
        return updated ? ResponseEntity.ok("Order status updated successfully")
                : ResponseEntity.badRequest().body("Failed to update order status");
    }

    @PatchMapping("/{orderId}/payment")
    public ResponseEntity<String> updateOrderPaymentStatus(@PathVariable Long orderId, @RequestParam boolean isPaid) {
        boolean updated = orderService.updateOrderPaymentStatus(orderId, isPaid);
        return updated ? ResponseEntity.ok("Order payment status updated successfully")
                : ResponseEntity.badRequest().body("Failed to update payment status");
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.ok("Order deleted successfully");
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO orderRequest) {
        Order createdOrder = orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }
}
