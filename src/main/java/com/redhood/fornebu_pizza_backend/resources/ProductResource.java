package com.redhood.fornebu_pizza_backend.resources;

import com.redhood.fornebu_pizza_backend.entity.Product;
import com.redhood.fornebu_pizza_backend.entity.ProductExtraDressing;
import com.redhood.fornebu_pizza_backend.entity.ProductSelectOption;

import java.util.List;
import java.util.stream.Collectors;

public class ProductResource {
    private Long id;
    private String name;
    private String description;
    private String allerg;
    private int stock;
    private double amount;
    private String imgURl;

    private String category;

    private List<ProductSelectOption> selectOptions;

    private List<String> deselectOptions;

    private List<ProductExtraDressing> extraDressing;

    private List<ProductBasicResource> extras;

    private List<ProductBasicResource> addDrinks;

    public ProductResource(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.allerg = product.getAllerg();
        this.stock = product.getStock();
        this.amount = product.getAmount();
        this.imgURl = product.getImgURl();
        this.category = (product.getCategory() != null) ? product.getCategory().getName() : null; // ✅ Get category name

        this.selectOptions = product.getSelectOptions();
        this.deselectOptions = product.getDeselectOptions();
        this.extraDressing = product.getExtraDressing();

        this.extras = product.getExtras().stream()
                .map(ProductBasicResource::new)
                .collect(Collectors.toList());

        this.addDrinks = product.getAddDrinks().stream()
                .map(ProductBasicResource::new)
                .collect(Collectors.toList());
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

    public String getCategory() {
        return category;
    }

    public List<ProductSelectOption> getSelectOptions() {
        return selectOptions;
    }

    public List<String> getDeselectOptions() {
        return deselectOptions;
    }

    public List<ProductExtraDressing> getExtraDressing() {
        return extraDressing;
    }

    public List<ProductBasicResource> getExtras() {
        return extras;
    }

    public List<ProductBasicResource> getAddDrinks() {
        return addDrinks;
    }
}
