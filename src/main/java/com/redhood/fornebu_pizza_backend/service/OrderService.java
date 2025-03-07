package com.redhood.fornebu_pizza_backend.service;

import com.redhood.fornebu_pizza_backend.dto.OrderProductDTO;
import com.redhood.fornebu_pizza_backend.dto.OrderProductOptionDTO;
import com.redhood.fornebu_pizza_backend.dto.OrderRequestDTO;
import com.redhood.fornebu_pizza_backend.embedded.OrderProductOption;
import com.redhood.fornebu_pizza_backend.entity.Order;
import com.redhood.fornebu_pizza_backend.entity.OrderProduct;
import com.redhood.fornebu_pizza_backend.entity.Product;
import com.redhood.fornebu_pizza_backend.repository.OrderRepository;
import com.redhood.fornebu_pizza_backend.repository.ProductRepository;
import com.redhood.fornebu_pizza_backend.resources.OrderSimpleResource;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
    }

    public List<OrderSimpleResource> getSimpleOrdersByUserId(String uid) {
        return orderRepository.findSimpleOrdersByUid(uid);
    }

    public List<OrderSimpleResource> getOrdersByStatus(String status) {
        if ("all".equalsIgnoreCase(status)) {
            return orderRepository.findAllSimpleOrders();
        }
        return orderRepository.findSimpleOrdersByOrderStatus(status);
    }

    public boolean updateOrderStatus(Long orderId, String status) {
        return orderRepository.updateOrderStatus(orderId, status) > 0;
    }

    public boolean updateOrderPaymentStatus(Long orderId, boolean isPaid) {
        return orderRepository.updateOrderPaymentStatus(orderId, isPaid) > 0;
    }

    public void deleteOrderById(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new EntityNotFoundException("Order with ID " + orderId + " not found");
        }
        orderRepository.deleteById(orderId);
    }

    public Order createOrder(OrderRequestDTO orderRequest) {
        Order order = new Order();
        order.setUid(orderRequest.getUid());
        order.setUserName(orderRequest.getUserName());
        order.setEmail(orderRequest.getEmail());
        order.setPhoneNumber1(orderRequest.getPhoneNumber1());
        order.setPhoneNumber2(orderRequest.getPhoneNumber2());
        order.setNote(orderRequest.getNote());
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setPaid(orderRequest.isPaid());
        order.setOrderStatus(orderRequest.getOrderStatus());

        order.setSubTotal(orderRequest.getSubTotal());
        order.setServiceCharge(orderRequest.getServiceCharge());
        order.setTotal(orderRequest.getTotal());

        // Calculate estimated time (Example: 15 mins from order time)
        order.setEstimatedTime(LocalDateTime.now().plusMinutes(15));

        List<OrderProduct> orderProducts = new ArrayList<>();

        for (OrderProductDTO productDTO : orderRequest.getProducts()) {
            Product product = productRepository.findById(productDTO.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found: " + productDTO.getProductId()));

            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(product);
            orderProduct.setQuantity(productDTO.getQuantity());
            orderProduct.setNote(productDTO.getNote());
            orderProduct.setDeselectOptions(productDTO.getDeselectOptions());

            orderProduct.setSelections(convertToOptions(productDTO.getSelections()));
            orderProduct.setExtras(convertToOptions(productDTO.getExtras()));
            orderProduct.setExtraDressing(convertToOptions(productDTO.getExtraDressing()));
            orderProduct.setAddDrinks(convertToOptions(productDTO.getAddDrinks()));

            orderProducts.add(orderProduct);
        }

        order.setProducts(orderProducts);
        return orderRepository.save(order);
    }
    private List<OrderProductOption> convertToOptions(List<OrderProductOptionDTO> optionDTOs) {
        if (optionDTOs == null) return new ArrayList<>();
        return optionDTOs.stream()
                .map(dto -> new OrderProductOption(dto.getName(), dto.getPrice(), dto.getQuantity()))
                .collect(Collectors.toList());
    }
}
