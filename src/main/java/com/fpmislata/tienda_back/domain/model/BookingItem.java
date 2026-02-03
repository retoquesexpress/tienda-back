package com.fpmislata.tienda_back.domain.model;

public class BookingItem {
    private String idItemBooking;
    private int quantity;

    public BookingItem(String id_booking, int quantity) {
        this.idItemBooking = id_booking;
        this.quantity = quantity;
    }

    public String getId_booking() {
        return idItemBooking;
    }

    public void setId_booking(String id_booking) {
        this.idItemBooking = id_booking;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
