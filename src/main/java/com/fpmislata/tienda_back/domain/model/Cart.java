package com.fpmislata.tienda_back.domain.model;

import java.util.List;

public class Cart {
    private int idCart;
    private int total_products;
    private double total_price;
    private List<CartItem> items;

    public Cart(int idCart, int total_products, double total_price, List<CartItem> items) {
        this.idCart = idCart;
        this.total_products = total_products;
        this.total_price = total_price;
        this.items = items;
    }

    public int getId_cart() {
        return idCart;
    }

    public void setId_cart(int idCart) {
        this.idCart = idCart;
    }

    public int getTotal_products() {
        return total_products;
    }

    public void setTotal_products(int total_products) {
        this.total_products = total_products;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
