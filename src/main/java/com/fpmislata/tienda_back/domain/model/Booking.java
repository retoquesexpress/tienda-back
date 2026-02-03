package com.fpmislata.tienda_back.domain.model;

import java.util.List;

public class Booking {
    private int idBooking;
    private int total_products;
    private double total_price;
    private List<BookingItem> items;

    public Booking(int idBooking, int total_products, double total_price, List<BookingItem> items) {
        this.idBooking = idBooking;
        this.total_products = total_products;
        this.total_price = total_price;
        this.items = items;
    }

    public int getId_booking() {
        return idBooking;
    }

    public void setId_booking(int idBooking) {
        this.idBooking = idBooking;
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

    public List<BookingItem> getItems() {
        return items;
    }

    public void setItems(List<BookingItem> items) {
        this.items = items;
    }
}
