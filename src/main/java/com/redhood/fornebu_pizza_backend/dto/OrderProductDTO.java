package com.redhood.fornebu_pizza_backend.dto;

import java.util.List;

public class OrderProductDTO {
    private Long productId;
    private int quantity;
    private String note;
    private List<String> deselectOptions;
    private List<OrderProductOptionDTO> selections;
    private List<OrderProductOptionDTO> extras;
    private List<OrderProductOptionDTO> extraDressing;
    private List<OrderProductOptionDTO> addDrinks;

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getNote() {
        return note;
    }

    public List<String> getDeselectOptions() {
        return deselectOptions;
    }

    public List<OrderProductOptionDTO> getSelections() {
        return selections;
    }

    public List<OrderProductOptionDTO> getExtras() {
        return extras;
    }

    public List<OrderProductOptionDTO> getExtraDressing() {
        return extraDressing;
    }

    public List<OrderProductOptionDTO> getAddDrinks() {
        return addDrinks;
    }
}
