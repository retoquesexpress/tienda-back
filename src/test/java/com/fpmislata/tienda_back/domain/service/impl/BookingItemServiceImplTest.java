package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.domain.repository.BookingItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingItemServiceImplTest {

    @Mock
    private BookingItemRepository bookingItemRepository;

    @InjectMocks
    private BookingItemServiceImpl bookingItemService;

    @Test
    @DisplayName("Should call repository increaseQuantityById when increaseQuantityById is called")
    void shouldCallRepositoryIncreaseQuantityById() {
        Integer id = 1;

        bookingItemService.increaseQuantityById(id);

        verify(bookingItemRepository, times(1)).increaseQuantityById(id);
    }

    @Test
    @DisplayName("Should call repository decreaseQuantityById when decreaseQuantityById is called")
    void shouldCallRepositoryDecreaseQuantityById() {
        Integer id = 1;

        bookingItemService.decreaseQuantityById(id);

        verify(bookingItemRepository, times(1)).decreaseQuantityById(id);
    }
}
