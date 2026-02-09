package com.fpmislata.tienda_back.domain.repository.entity;

import java.time.LocalDateTime;

public record BookingItemEntity(
        Integer idBookingItem,
        Integer idService,
        Integer quantity,
        LocalDateTime bookingDate,
        ServiceEntity service) {
    public BookingItemEntity(
            Integer idBookingItem,
            Integer idService,
            Integer quantity,
            LocalDateTime bookingDate,
            ServiceEntity service) {
        this.idBookingItem = idBookingItem;
        this.idService = idService;
        this.quantity = quantity;
        this.bookingDate = bookingDate;
        this.service = service;
    }
}
