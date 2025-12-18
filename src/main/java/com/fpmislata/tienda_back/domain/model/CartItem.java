package com.fpmislata.tienda_back.domain.model;

public class CartItem {
    private String idItemCart;
    private int quantity;

    public CartItem(String id_cart, int quantity) {
        this.idItemCart = id_cart;
        this.quantity = quantity;
    }

    public String getId_cart() {
        return idItemCart;
    }

    public void setId_cart(String id_cart) {
        this.idItemCart = id_cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
