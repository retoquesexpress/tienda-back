package com.fpmislata.tienda_back.domain.model;

import java.util.List;

public class Booking {
    private Integer idBooking;
    private double total_price;
    private List<BookingItem> items;
    private User user;

    public Booking(Integer idBooking, double total_price, List<BookingItem> items, User user) {
        this.idBooking = idBooking;
        this.total_price = items.stream().mapToDouble(item -> item.getService().getPrice() * item.getQuantity()).sum();
        this.items = items;
        this.user = user;
    }

    public List<BookingItem> findAllBookingsByUserWhenBookingDateIsFuture() {
        return this.items.stream()
                .filter(item -> item.getBookingDate().isAfter(java.time.LocalDate.now()))
                .collect(java.util.stream.Collectors.toList());
    }

    public Integer getId_booking() {
        return idBooking;
    }

    public void setId_booking(Integer idBooking) {
        this.idBooking = idBooking;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
