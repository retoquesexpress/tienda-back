package com.fpmislata.tienda_back.persistence.dao.jpa.impl;

import com.fpmislata.tienda_back.persistence.dao.jpa.BookingJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.BookingJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookingJpaDaoImpl implements BookingJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BookingJpaEntity> findAll() {
        return entityManager.createQuery("SELECT b FROM BookingJpaEntity b", BookingJpaEntity.class)
                .getResultList();
    }

    @Override
    public Optional<BookingJpaEntity> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(BookingJpaEntity.class, id));
    }

    @Override
    public BookingJpaEntity save(BookingJpaEntity bookingJpaEntity) {
        if (bookingJpaEntity.getIdBooking() == null) {
            entityManager.persist(bookingJpaEntity);
            return bookingJpaEntity;
        } else {
            return entityManager.merge(bookingJpaEntity);
        }
    }

    @Override
    public List<BookingJpaEntity> findAllBookingsByUserWhenBookingDateIsFuture(Integer idUser) {
        return entityManager.createQuery(
                "SELECT DISTINCT b FROM BookingJpaEntity b JOIN b.bookingItems bi " +
                "WHERE b.user.idUser = :idUser AND bi.bookingDate > CURRENT_DATE", BookingJpaEntity.class)
                .setParameter("idUser", idUser)
                .getResultList();
    }

    @Override
    public void delete(Integer id) {
        BookingJpaEntity entity = entityManager.find(BookingJpaEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }
}
