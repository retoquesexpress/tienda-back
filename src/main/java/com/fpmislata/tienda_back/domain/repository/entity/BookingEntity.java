package com.fpmislata.tienda_back.domain.repository.entity;

import java.util.List;

public record BookingEntity(
        Integer idBooking,
        double total_price,
        List<BookingItemEntity> items,
        UserEntity user
) {
    public BookingEntity(Integer idBooking, double total_price, List<BookingItemEntity> items, UserEntity user) {
        this.idBooking = idBooking;
        this.total_price = total_price;
        this.items = items;
        this.user = user;
    }
}

