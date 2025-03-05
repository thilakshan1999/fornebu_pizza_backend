package com.redhood.fornebu_pizza_backend.dto;

import java.util.List;

public class OrderRequestDTO {
    private String uid;
    private String userName;
    private String email;
    private String phoneNumber1;
    private String phoneNumber2;
    private String note;
    private String paymentMethod;
    private boolean isPaid;
    private String orderStatus;

    private double subTotal;
    private double serviceCharge;
    private double total;

    private List<OrderProductDTO> products;

    public String getUid() {
        return uid;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public String getNote() {
        return note;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public double getTotal() {
        return total;
    }

    public List<OrderProductDTO> getProducts() {
        return products;
    }
}
