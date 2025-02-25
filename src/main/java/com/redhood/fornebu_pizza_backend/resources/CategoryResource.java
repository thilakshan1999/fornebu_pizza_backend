package com.redhood.fornebu_pizza_backend.resources;

import com.redhood.fornebu_pizza_backend.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryResource {
    private Long id;
    private String name;

    private List<ProductResource> products;

    // Constructor

    public CategoryResource(Long id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products != null
                ? products.stream().map(ProductResource::new).collect(Collectors.toList())
                : null;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ProductResource> getProducts() {
        return products;
    }
}
