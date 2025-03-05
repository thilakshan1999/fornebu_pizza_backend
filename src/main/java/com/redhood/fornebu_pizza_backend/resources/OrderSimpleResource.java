package com.redhood.fornebu_pizza_backend.resources;

import java.time.LocalDateTime;

public class OrderSimpleResource {
    private Long id;
    private String uid;
    private String paymentMethod;
    private boolean isPaid;
    private LocalDateTime createdAt;
    private LocalDateTime estimatedTime;
    private String orderStatus;

    // Constructor
    public OrderSimpleResource(Long id, String uid, String paymentMethod, boolean isPaid,
                               LocalDateTime createdAt, LocalDateTime estimatedTime,
                               String orderStatus) {
        this.id = id;
        this.uid = uid;
        this.paymentMethod = paymentMethod;
        this.isPaid = isPaid;
        this.createdAt = createdAt;
        this.estimatedTime = estimatedTime;
        this.orderStatus = orderStatus;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUid() { return uid; }
    public void setUid(String uid) { this.uid = uid; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public boolean isPaid() { return isPaid; }
    public void setPaid(boolean isPaid) { this.isPaid = isPaid; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getEstimatedTime() { return estimatedTime; }
    public void setEstimatedTime(LocalDateTime estimatedTime) { this.estimatedTime = estimatedTime; }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
}
