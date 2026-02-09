package com.fpmislata.tienda_back.persistence.repository;

import com.fpmislata.tienda_back.domain.repository.BookingRepository;
import com.fpmislata.tienda_back.domain.repository.entity.BookingEntity;
import com.fpmislata.tienda_back.mapper.BookingMapper;
import com.fpmislata.tienda_back.persistence.dao.jpa.BookingJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.ServiceJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.BookingJpaEntity;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.ServiceJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookingRepositoryImpl implements BookingRepository {

    private final BookingJpaDao bookingJpaDao;
    private final ServiceJpaDao serviceJpaDao;

    public BookingRepositoryImpl(BookingJpaDao bookingJpaDao, ServiceJpaDao serviceJpaDao) {
        this.bookingJpaDao = bookingJpaDao;
        this.serviceJpaDao = serviceJpaDao;
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

        if (bookingEntity.items() != null) {
            double totalPrice = 0;
            List<com.fpmislata.tienda_back.persistence.dao.jpa.entity.BookingItemJpaEntity> itemJpaEntities = new java.util.ArrayList<>();

            for (var item : bookingEntity.items()) {
                var itemJpa = com.fpmislata.tienda_back.mapper.BookingItemMapper.getInstance()
                        .fromBookingItemEntityToBookingItemJpaEntity(item);

                Integer serviceId = (item.service() != null) ? item.service().idService() : item.idService();
                if (serviceId != null) {
                    Optional<ServiceJpaEntity> serviceJpa = serviceJpaDao.findById(serviceId);
                    if (serviceJpa.isPresent()) {
                        ServiceJpaEntity s = serviceJpa.get();
                        itemJpa.setService(s);
                        totalPrice += (s.getPrice() != null ? s.getPrice() : 0.0) * item.quantity();
                    }
                }

                itemJpa.setBooking(jpaEntity);
                itemJpaEntities.add(itemJpa);
            }
            jpaEntity.setBookingItems(itemJpaEntities);
            jpaEntity.setTotalPrice(totalPrice);
        }

        BookingJpaEntity savedEntity = bookingJpaDao.save(jpaEntity);
        return BookingMapper.getInstance().fromBookingJpaEntityToBookingEntity(savedEntity);
    }

    @Override
    public void delete(Integer id) {
        bookingJpaDao.delete(id);
    }
}
