package com.rentalplatform.equipmentrental.model.dto;

import com.rentalplatform.equipmentrental.model.CartItem;
import java.math.BigDecimal;
import java.util.List;

public class CartResponse {
    private List<CartItem> items;
    private BigDecimal totalPrice;

    // Constructors
    public CartResponse() {}

    public CartResponse(List<CartItem> items, BigDecimal totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}