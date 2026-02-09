package com.fpmislata.tienda_back.persistence.repository;

import com.fpmislata.tienda_back.persistence.dao.jpa.BookingItemJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.BookingItemJpaEntity;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.ServiceJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingItemRepositoryImplTest {

    @Mock
    private BookingItemJpaDao bookingItemJpaDao;

    @InjectMocks
    private BookingItemRepositoryImpl bookingItemRepository;

    private BookingItemJpaEntity bookingItemJpaEntity;

    @BeforeEach
    void setUp() {
        ServiceJpaEntity service = new ServiceJpaEntity();
        service.setIdService(1);
        service.setName("Test Service");
        service.setPrice(10.0); // Fix NPE in mapping

        bookingItemJpaEntity = new BookingItemJpaEntity();
        bookingItemJpaEntity.setIdBookingItem(1);
        bookingItemJpaEntity.setQuantity(2);
        bookingItemJpaEntity.setBookingDate(LocalDateTime.now());
        bookingItemJpaEntity.setService(service);
    }

    @Test
    @DisplayName("Should increase quantity when increaseQuantityById is called")
    void shouldIncreaseQuantity() {
        Integer id = 1;
        when(bookingItemJpaDao.findById(id)).thenReturn(Optional.of(bookingItemJpaEntity));
        when(bookingItemJpaDao.save(any(BookingItemJpaEntity.class))).thenAnswer(i -> {
            BookingItemJpaEntity entity = i.getArgument(0);
            if (entity.getService() != null)
                entity.getService().setPrice(10.0); // Fix NPE
            return entity;
        });

        bookingItemRepository.increaseQuantityById(id);

        verify(bookingItemJpaDao, times(2)).findById(id);
        verify(bookingItemJpaDao, times(1)).save(argThat(entity -> entity.getQuantity() == 3));
    }

    @Test
    @DisplayName("Should decrease quantity when decreaseQuantityById is called and quantity > 1")
    void shouldDecreaseQuantity() {
        Integer id = 1;
        bookingItemJpaEntity.setQuantity(2);
        when(bookingItemJpaDao.findById(id)).thenReturn(Optional.of(bookingItemJpaEntity));
        when(bookingItemJpaDao.save(any(BookingItemJpaEntity.class))).thenAnswer(i -> {
            BookingItemJpaEntity entity = i.getArgument(0);
            if (entity.getService() != null)
                entity.getService().setPrice(10.0); // Fix NPE
            return entity;
        });

        bookingItemRepository.decreaseQuantityById(id);

        verify(bookingItemJpaDao, times(2)).findById(id);
        verify(bookingItemJpaDao, times(1)).save(argThat(entity -> entity.getQuantity() == 1));
    }

    @Test
    @DisplayName("Should NOT decrease quantity when decreaseQuantityById is called and quantity == 1")
    void shouldNotDecreaseQuantityWhenQuantityIsOne() {
        Integer id = 1;
        bookingItemJpaEntity.setQuantity(1);
        when(bookingItemJpaDao.findById(id)).thenReturn(Optional.of(bookingItemJpaEntity));

        bookingItemRepository.decreaseQuantityById(id);

        verify(bookingItemJpaDao, times(1)).findById(id);
        verify(bookingItemJpaDao, never()).save(any());
    }
}
