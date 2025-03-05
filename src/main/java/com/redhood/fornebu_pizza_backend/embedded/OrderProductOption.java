package com.redhood.fornebu_pizza_backend.embedded;

import jakarta.persistence.Embeddable;

@Embeddable
public class OrderProductOption {
    private String name;
    private double price;
    private int quantity;

    // Constructors
    public OrderProductOption() {}

    public OrderProductOption(String name, double price,int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
