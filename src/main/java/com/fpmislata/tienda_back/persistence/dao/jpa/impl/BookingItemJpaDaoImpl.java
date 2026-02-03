package com.fpmislata.tienda_back.persistence.dao.jpa.impl;

import com.fpmislata.tienda_back.persistence.dao.jpa.BookingItemJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.BookingItemJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class BookingItemJpaDaoImpl implements BookingItemJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BookingItemJpaEntity> findAll() {
        return entityManager.createQuery("SELECT b FROM BookingItemJpaEntity b", BookingItemJpaEntity.class)
                .getResultList();
    }

    @Override
    public Optional<BookingItemJpaEntity> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(BookingItemJpaEntity.class, id));
    }

    @Override
    public BookingItemJpaEntity save(BookingItemJpaEntity bookingItemJpaEntity) {
        if (bookingItemJpaEntity.getIdBookingItem() == null) {
            entityManager.persist(bookingItemJpaEntity);
            return bookingItemJpaEntity;
        } else {
            return entityManager.merge(bookingItemJpaEntity);
        }
    }

    @Override
    public void delete(Integer id) {
        BookingItemJpaEntity entity = entityManager.find(BookingItemJpaEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }
}
