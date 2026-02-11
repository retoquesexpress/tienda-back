package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.domain.repository.BookingRepository;
import com.fpmislata.tienda_back.domain.repository.entity.BookingEntity;
import com.fpmislata.tienda_back.domain.repository.entity.UserEntity;
import com.fpmislata.tienda_back.domain.service.dto.BookingDto;
import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import com.fpmislata.tienda_back.exception.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Nested
    @DisplayName("Tests for findAll method")
    class TestsFindAll {
        @Test
        @DisplayName("findAll should return list of BookingDto when bookings exist")
        void findAll_ShouldReturnListOfBookingDto_WhenBookingsExist() {
            UserEntity userEntity = new UserEntity(1, null, null, null, null, null, null, null, null);
            BookingEntity bookingEntity = new BookingEntity(1, 100.0, new ArrayList<>(), userEntity);
            when(bookingRepository.findAll()).thenReturn(List.of(bookingEntity));

            List<BookingDto> result = bookingService.findAll();

            assertEquals(1, result.size());
            assertEquals(1, result.get(0).idBooking());
            assertEquals(100.0, result.get(0).total_price());
            verify(bookingRepository, times(1)).findAll();
        }
    }

    @Nested
    @DisplayName("Tests for findAllBookingsByUserWhenBookingDateIsFuture method")
    class TestsFindAllByUserInFuture {
        @Test
        @DisplayName("findAllBookingsByUserWhenBookingDateIsFuture should return list or empty")
        void findAllBookingsByUserWhenBookingDateIsFuture_ShouldReturnList() {
            Integer userId = 1;
            UserEntity userEntity = new UserEntity(userId, null, null, null, null, null, null, null, null);
            BookingEntity bookingEntity = new BookingEntity(1, 100.0, new ArrayList<>(), userEntity);
            when(bookingRepository.findAllBookingsByUserWhenBookingDateIsFuture(userId)).thenReturn(List.of(bookingEntity));

            List<BookingDto> result = bookingService.findAllBookingsByUserWhenBookingDateIsFuture(userId);

            assertEquals(1, result.size());
            assertEquals(userId, result.get(0).user().idUser());
            verify(bookingRepository, times(1)).findAllBookingsByUserWhenBookingDateIsFuture(userId);
        }
    }

    @Nested
    @DisplayName("Tests for findById method")
    class TestsFindById {
        @Test
        @DisplayName("findById should return BookingDto when exists")
        void findById_ShouldReturnBookingDto_WhenExists() {
            Integer id = 1;
            UserEntity userEntity = new UserEntity(1, null, null, null, null, null, null, null, null);
            BookingEntity bookingEntity = new BookingEntity(id, 100.0, new ArrayList<>(), userEntity);
            when(bookingRepository.findById(id)).thenReturn(Optional.of(bookingEntity));

            BookingDto result = bookingService.findById(id);

            assertNotNull(result);
            assertEquals(id, result.idBooking());
            verify(bookingRepository, times(1)).findById(id);
        }

        @Test
        @DisplayName("findById should throw ResourceNotFoundException when not exists")
        void findById_ShouldThrowException_WhenNotExists() {
            Integer id = 1;
            when(bookingRepository.findById(id)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> bookingService.findById(id));
            verify(bookingRepository, times(1)).findById(id);
        }
    }

    @Nested
    @DisplayName("Tests for save method")
    class TestsSave {
        @Test
        @DisplayName("save should return saved BookingDto")
        void save_ShouldReturnSavedBookingDto() {
            UserDto userDto = new UserDto(1, null, null, null, null, null, null, null, null);
            BookingDto bookingDto = new BookingDto(1, 100.0, new ArrayList<>(), userDto);
            UserEntity userEntity = new UserEntity(1, null, null, null, null, null, null, null, null);
            BookingEntity bookingEntity = new BookingEntity(1, 100.0, new ArrayList<>(), userEntity);

            when(bookingRepository.save(any(BookingEntity.class))).thenReturn(bookingEntity);

            BookingDto result = bookingService.save(bookingDto);

            assertNotNull(result);
            assertEquals(1, result.idBooking());
            verify(bookingRepository, times(1)).save(any(BookingEntity.class));
        }
    }

    @Nested
    @DisplayName("Tests for delete method")
    class TestsDelete {
        @Test
        @DisplayName("delete should call repository delete")
        void delete_ShouldCallRepositoryDelete() {
            Integer id = 1;
            bookingService.delete(id);
            verify(bookingRepository, times(1)).delete(id);
        }
    }
}
