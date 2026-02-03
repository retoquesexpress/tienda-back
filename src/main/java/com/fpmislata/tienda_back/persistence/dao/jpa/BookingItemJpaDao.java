package com.fpmislata.tienda_back.persistence.dao.jpa;

import com.fpmislata.tienda_back.persistence.dao.jpa.entity.BookingItemJpaEntity;
import java.util.List;
import java.util.Optional;

public interface BookingItemJpaDao {
    List<BookingItemJpaEntity> findAll();

    Optional<BookingItemJpaEntity> findById(Integer id);

    BookingItemJpaEntity save(BookingItemJpaEntity bookingItemJpaEntity);

    void delete(Integer id);
}
