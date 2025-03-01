package com.redhood.fornebu_pizza_backend.resources;

import com.redhood.fornebu_pizza_backend.entity.Product;

public class ProductBasicResource {
    private Long id;
    private String name;
    private double amount;

    private int stock;

    public ProductBasicResource(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.amount = product.getAmount();
        this.stock = product.getStock();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public int getStock() {
        return stock;
    }
}
