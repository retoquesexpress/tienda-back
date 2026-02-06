package com.fpmislata.tienda_back.domain.repository;

import com.fpmislata.tienda_back.domain.repository.entity.BookingEntity;
import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    List<BookingEntity> findAll();

    List<BookingEntity> findAllBookingsByUserWhenBookingDateIsFuture(Integer idUser);

    Optional<BookingEntity> findById(Integer id);

    BookingEntity save(BookingEntity bookingEntity);

    void delete(Integer id);
}

