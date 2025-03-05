package com.redhood.fornebu_pizza_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.redhood.fornebu_pizza_backend.embedded.OrderProductOption;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int quantity;
    private String note;

    @ElementCollection
    @CollectionTable(name = "order_product_deselect_options", joinColumns = @JoinColumn(name = "order_product_id"))
    private List<String> deselectOptions;

    @ElementCollection
    @CollectionTable(name = "order_product_selections", joinColumns = @JoinColumn(name = "order_product_id"))
    private List<OrderProductOption> selections;

    @ElementCollection
    @CollectionTable(name = "order_product_extras", joinColumns = @JoinColumn(name = "order_product_id"))
    private List<OrderProductOption> extras;

    @ElementCollection
    @CollectionTable(name = "order_product_extra_dressing", joinColumns = @JoinColumn(name = "order_product_id"))
    private List<OrderProductOption> extraDressing;

    @ElementCollection
    @CollectionTable(name = "order_product_add_drinks", joinColumns = @JoinColumn(name = "order_product_id"))
    private List<OrderProductOption> addDrinks;


    public OrderProduct() {
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
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

    public List<OrderProductOption> getSelections() {
        return selections;
    }

    public List<OrderProductOption> getExtras() {
        return extras;
    }

    public List<OrderProductOption> getExtraDressing() {
        return extraDressing;
    }

    public List<OrderProductOption> getAddDrinks() {
        return addDrinks;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDeselectOptions(List<String> deselectOptions) {
        this.deselectOptions = deselectOptions;
    }

    public void setSelections(List<OrderProductOption> selections) {
        this.selections = selections;
    }

    public void setExtras(List<OrderProductOption> extras) {
        this.extras = extras;
    }

    public void setExtraDressing(List<OrderProductOption> extraDressing) {
        this.extraDressing = extraDressing;
    }

    public void setAddDrinks(List<OrderProductOption> addDrinks) {
        this.addDrinks = addDrinks;
    }
}
