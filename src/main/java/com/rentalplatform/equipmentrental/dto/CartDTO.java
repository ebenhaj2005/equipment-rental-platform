package com.rentalplatform.equipmentrental.dto;

import java.util.List;

public class CartDTO {
    private Long id;
    private Double totalPrice;
    private List<CartItemDTO> items;

    public CartDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }
}