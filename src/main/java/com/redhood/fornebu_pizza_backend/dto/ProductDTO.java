package com.redhood.fornebu_pizza_backend.dto;

import java.util.List;

public class ProductDTO {
    private String name;
    private String description;
    private boolean allerg;
    private int stock;
    private double amount;
    private String imgURl;
    private Long categoryId;

    private List<ProductOptionDTO> selectOptions;

    private  List<String> deselectOptions;
    private List<ProductOptionDTO> extraDressings;

    private  List<Long> extras;

    private  List<Long> addDrinks;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAllerg() {
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

    public Long getCategoryId() {
        return categoryId;
    }

    public List<ProductOptionDTO> getSelectOptions() {
        return selectOptions;
    }

    public List<ProductOptionDTO> getExtraDressings() {
        return extraDressings;
    }

    public List<String> getDeselectOptions() {
        return deselectOptions;
    }

    public List<Long> getExtras() {
        return extras;
    }

    public List<Long> getAddDrinks() {
        return addDrinks;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", allerg=" + allerg +
                ", stock=" + stock +
                ", amount=" + amount +
                ", imgURl='" + imgURl + '\'' +
                ", categoryId=" + categoryId +
                ", selectOptions=" + selectOptions +
                ", deselectOptions=" + deselectOptions +
                ", extraDressings=" + extraDressings +
                ", extras=" + extras +
                ", addDrinks=" + addDrinks +
                '}';
    }
}
