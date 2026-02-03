package com.fpmislata.tienda_back.persistence.repository;

import com.fpmislata.tienda_back.domain.model.BookingItem;
import com.fpmislata.tienda_back.domain.repository.BookingItemRepository;
import com.fpmislata.tienda_back.mapper.BookingItemMapper;
import com.fpmislata.tienda_back.persistence.dao.jpa.BookingItemJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.BookingItemJpaEntity;
import java.util.List;
import java.util.stream.Collectors;

public class BookingItemRepositoryImpl implements BookingItemRepository {

    private final BookingItemJpaDao bookingItemJpaDao;

    public BookingItemRepositoryImpl(BookingItemJpaDao bookingItemJpaDao) {
        this.bookingItemJpaDao = bookingItemJpaDao;
    }

    @Override
    public List<BookingItem> findAll() {
        return bookingItemJpaDao.findAll().stream()
                .map(BookingItemMapper.getInstance()::fromBookingItemJpaEntityToBookingItem)
                .collect(Collectors.toList());
    }

    @Override
    public BookingItem findById(Integer id) {
        return bookingItemJpaDao.findById(id)
                .map(BookingItemMapper.getInstance()::fromBookingItemJpaEntityToBookingItem)
                .orElse(null);
    }

    @Override
    public BookingItem save(BookingItem bookingItem) {
        BookingItemJpaEntity entity = BookingItemMapper.getInstance()
                .fromBookingItemToBookingItemJpaEntity(bookingItem);
        BookingItemJpaEntity savedEntity = bookingItemJpaDao.save(entity);
        return BookingItemMapper.getInstance().fromBookingItemJpaEntityToBookingItem(savedEntity);
    }

    @Override
    public void delete(Integer id) {
        bookingItemJpaDao.delete(id);
    }
}
