package com.fpmislata.tienda_back.persistence.repository;

import com.fpmislata.tienda_back.domain.model.BookingItem;
import com.fpmislata.tienda_back.domain.repository.BookingItemRepository;
import com.fpmislata.tienda_back.domain.repository.entity.BookingItemEntity;
import com.fpmislata.tienda_back.mapper.BookingItemMapper;
import com.fpmislata.tienda_back.persistence.dao.jpa.BookingItemJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.BookingItemJpaEntity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingItemRepositoryImpl implements BookingItemRepository {

    private final BookingItemJpaDao bookingItemJpaDao;

    public BookingItemRepositoryImpl(BookingItemJpaDao bookingItemJpaDao) {
        this.bookingItemJpaDao = bookingItemJpaDao;
    }

    @Override
    public List<BookingItemEntity> findAll() {
        return bookingItemJpaDao.findAll().stream()
                .map(BookingItemMapper.getInstance()::fromBookingItemJpaEntityToBookingItemEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookingItemEntity> findById(Integer id) {
        return bookingItemJpaDao.findById(id)
                .map(BookingItemMapper.getInstance()::fromBookingItemJpaEntityToBookingItemEntity);
    }

    @Override
    public BookingItemEntity save(BookingItemEntity bookingItemEntity) {
        BookingItemJpaEntity entity = BookingItemMapper.getInstance()
                .fromBookingItemEntityToBookingItemJpaEntity(bookingItemEntity);

        if (entity.getIdBookingItem() != null) {
            bookingItemJpaDao.findById(entity.getIdBookingItem()).ifPresent(existing -> {
                entity.setBooking(existing.getBooking());
            });
        }

        BookingItemJpaEntity savedEntity = bookingItemJpaDao.save(entity);
        return BookingItemMapper.getInstance().fromBookingItemJpaEntityToBookingItemEntity(savedEntity);
    }

    @Override
    public void delete(Integer id) {
        bookingItemJpaDao.delete(id);
    }

    @Override
    public void increaseQuantityById(Integer id) {
//        BookingItemEntity bookingItemEntity = findById(id).orElse(null);
//        if (bookingItemEntity != null) {
//            bookingItemEntity.setQuantity(bookingItemEntity.getQuantity() + 1);
//            save(bookingItemEntity);
//        }
        findById(id).ifPresent(item -> {
            BookingItemEntity updated = new BookingItemEntity(
                    item.idBookingItem(),
                    item.idService(),
                    item.quantity() + 1,
                    item.bookingDate(),
                    item.service()
            );
            save(updated);
        });
    }

    @Override
    public void decreaseQuantityById(Integer id) {
//        BookingItemEntity bookingItemEntity = findById(id).orElse(null);
//        if (bookingItemEntity != null && bookingItemEntity.getQuantity() > 1) {
//            bookingItemEntity.setQuantity(bookingItemEntity.getQuantity() - 1);
//            save(bookingItemEntity);
//        }
        findById(id).ifPresent(item -> {
            if (item.quantity() > 1) {
                BookingItemEntity updated = new BookingItemEntity(
                        item.idBookingItem(),
                        item.idService(),
                        item.quantity() - 1,
                        item.bookingDate(),
                        item.service()
                );
                save(updated);
            }
        });
    }
}
