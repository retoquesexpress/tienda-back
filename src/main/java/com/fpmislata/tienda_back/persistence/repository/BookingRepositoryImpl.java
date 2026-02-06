package com.fpmislata.tienda_back.persistence.repository;

import com.fpmislata.tienda_back.domain.repository.BookingRepository;
import com.fpmislata.tienda_back.domain.repository.entity.BookingEntity;
import com.fpmislata.tienda_back.mapper.BookingMapper;
import com.fpmislata.tienda_back.persistence.dao.jpa.BookingJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.BookingJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookingRepositoryImpl implements BookingRepository {

    private final BookingJpaDao bookingJpaDao;

    public BookingRepositoryImpl(BookingJpaDao bookingJpaDao) {
        this.bookingJpaDao = bookingJpaDao;
    }

    @Override
    public List<BookingEntity> findAll() {
        return bookingJpaDao.findAll().stream()
                .map(BookingMapper.getInstance()::fromBookingJpaEntityToBookingEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingEntity> findAllBookingsByUserWhenBookingDateIsFuture(Integer idUser) {
        return bookingJpaDao.findAllBookingsByUserWhenBookingDateIsFuture(idUser).stream()
                .map(BookingMapper.getInstance()::fromBookingJpaEntityToBookingEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookingEntity> findById(Integer id) {
        return bookingJpaDao.findById(id)
                .map(BookingMapper.getInstance()::fromBookingJpaEntityToBookingEntity);
    }

    @Override
    public BookingEntity save(BookingEntity bookingEntity) {
        BookingJpaEntity jpaEntity = BookingMapper.getInstance().fromBookingEntityToBookingJpaEntity(bookingEntity);
        BookingJpaEntity savedEntity = bookingJpaDao.save(jpaEntity);
        return BookingMapper.getInstance().fromBookingJpaEntityToBookingEntity(savedEntity);
    }

    @Override
    public void delete(Integer id) {
        bookingJpaDao.delete(id);
    }
}
