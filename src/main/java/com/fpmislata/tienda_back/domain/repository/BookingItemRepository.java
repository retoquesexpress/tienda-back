package com.fpmislata.tienda_back.domain.repository;

import com.fpmislata.tienda_back.domain.model.BookingItem;
import com.fpmislata.tienda_back.domain.repository.entity.BookingItemEntity;

import java.util.List;

import java.util.Optional;

public interface BookingItemRepository {
    List<BookingItemEntity> findAll();

    Optional<BookingItemEntity> findById(Integer id);

    BookingItemEntity save(BookingItemEntity bookingItem);

    void delete(Integer id);

    void increaseQuantityById(Integer id);

    void decreaseQuantityById(Integer id);
}
