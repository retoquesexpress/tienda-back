package com.fpmislata.tienda_back.domain.model;

public class CartItem {
    private String id_item_cart;
    private int quantity;

    public CartItem(String id_cart, int quantity) {
        this.id_item_cart = id_cart;
        this.quantity = quantity;
    }

    public String getId_cart() {
        return id_item_cart;
    }

    public void setId_cart(String id_cart) {
        this.id_item_cart = id_cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
