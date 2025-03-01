package com.redhood.fornebu_pizza_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;


@Entity

public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String allerg;
    private int stock;
    private double amount;
    private String imgURl;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)  // Foreign key column for category
    @JsonBackReference
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductSelectOption> selectOptions;

    @ElementCollection
    @CollectionTable(name = "product_deselect_options", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "deselect_option")
    private List<String> deselectOptions;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductExtraDressing> extraDressing;

    @ManyToMany
    @JoinTable(
            name = "product_extra_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "extra_product_id")
    )
    private List<Product> extras;

    @ManyToMany
    @JoinTable(
            name = "product_add_drink_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "add_drink_product_id")
    )
    private List<Product> addDrinks;
    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAllerg() {
        return allerg;
    }

    public int getStock() {
        return stock;
    }

    public double getAmount() {
        return amount;
    }

    public String getImgURl() {
        return imgURl;
    }

    public Category getCategory() {
        return category;
    }

    public List<ProductSelectOption> getSelectOptions() {
        return selectOptions;
    }

    public List<ProductExtraDressing> getExtraDressing() {
        return extraDressing;
    }

    public List<String> getDeselectOptions() {
        return deselectOptions;
    }

    public List<Product> getExtras() {
        return extras;
    }

    public List<Product> getAddDrinks() {
        return addDrinks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAllerg(String allerg) {
        this.allerg = allerg;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setImgURl(String imgURl) {
        this.imgURl = imgURl;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSelectOptions(List<ProductSelectOption> selectOptions) {
        this.selectOptions = selectOptions;
    }

    public void setExtraDressing(List<ProductExtraDressing> extraDressing) {
        this.extraDressing = extraDressing;
    }

    public void setDeselectOptions(List<String> deselectOptions) {
        this.deselectOptions = deselectOptions;
    }

    public void setExtras(List<Product> extras) {
        this.extras = extras;
    }

    public void setAddDrinks(List<Product> addDrinks) {
        this.addDrinks = addDrinks;
    }
}
