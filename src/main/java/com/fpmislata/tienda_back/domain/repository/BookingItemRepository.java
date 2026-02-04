package com.fpmislata.tienda_back.domain.repository;

import com.fpmislata.tienda_back.domain.model.BookingItem;
import java.util.List;

import java.util.Optional;

public interface BookingItemRepository {
    List<BookingItem> findAll();

    Optional<BookingItem> findById(Integer id);

    BookingItem save(BookingItem bookingItem);

    void delete(Integer id);

    void increaseQuantityById(Integer id);

    void decreaseQuantityById(Integer id);
}
