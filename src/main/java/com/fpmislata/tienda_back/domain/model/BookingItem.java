package com.fpmislata.tienda_back.domain.model;

import java.time.LocalDate;

public class BookingItem {

    private Integer idBookingItem;
    private int quantity;
    private LocalDate bookingDate;
    private Service service;

    public BookingItem() {
    }

    public BookingItem(Integer idBookingItem, int quantity, LocalDate bookingDate, Service service) {
        this.idBookingItem = idBookingItem;
        this.quantity = quantity;
        this.bookingDate = bookingDate;
        this.service = service;
    }


    public Integer getIdBookingItem() {
        return idBookingItem;
    }

    public void setIdBookingItem(Integer idBookingItem) {
        this.idBookingItem = idBookingItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
