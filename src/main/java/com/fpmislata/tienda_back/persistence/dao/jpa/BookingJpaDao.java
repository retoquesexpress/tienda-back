package com.fpmislata.tienda_back.persistence.dao.jpa;

import com.fpmislata.tienda_back.persistence.dao.jpa.entity.BookingJpaEntity;
import java.util.List;
import java.util.Optional;

public interface BookingJpaDao {
    List<BookingJpaEntity> findAll();
    Optional<BookingJpaEntity> findById(Integer id);
    BookingJpaEntity save(BookingJpaEntity bookingJpaEntity);
    List<BookingJpaEntity> findAllBookingsByUserWhenBookingDateIsFuture(Integer idUser);
    void delete(Integer id);
}


