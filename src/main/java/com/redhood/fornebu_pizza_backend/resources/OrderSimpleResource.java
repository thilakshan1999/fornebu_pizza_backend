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
    private  int itemCount;
    private  double total;

    private String userName;

    // Constructor
    public OrderSimpleResource(Long id, String uid, String paymentMethod, boolean isPaid,
                               LocalDateTime createdAt, LocalDateTime estimatedTime,
                               String orderStatus,double total,int itemCount,String userName) {
        this.id = id;
        this.uid = uid;
        this.paymentMethod = paymentMethod;
        this.isPaid = isPaid;
        this.createdAt = createdAt;
        this.estimatedTime = estimatedTime;
        this.orderStatus = orderStatus;
        this.itemCount = itemCount;
        this.total = total;
        this.userName = userName;
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

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
