package com.fpmislata.tienda_back.mapper;

import com.fpmislata.tienda_back.controller.webModel.request.BookingInsertRequest;
import com.fpmislata.tienda_back.controller.webModel.response.BookingDetailResponse;
import com.fpmislata.tienda_back.domain.model.Booking;
import com.fpmislata.tienda_back.domain.repository.entity.BookingEntity;
import com.fpmislata.tienda_back.domain.service.dto.BookingDto;
import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.BookingJpaEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BookingMapper {
    private static BookingMapper INSTANCE;

    private BookingMapper() {
    }

    public static BookingMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookingMapper();
        }
        return INSTANCE;
    }

    public BookingDto fromBookingToBookingDto(Booking booking) {
        if (booking == null) {
            return null;
        }
        return new BookingDto(
                booking.getId_booking(),
                booking.getTotal_price(),
                booking.getItems() != null ? booking.getItems().stream()
                        .map(BookingItemMapper.getInstance()::fromBookingItemToBookingItemDto)
                        .collect(Collectors.toList()) : new ArrayList<>(),
                UserMapper.getInstance().fromUserToUserDto(booking.getUser())
        );
    }

    public Booking fromBookingDtoToBooking(BookingDto bookingDto) {
        if (bookingDto == null) {
            return null;
        }
        return new Booking(
                bookingDto.idBooking(),
                bookingDto.total_price(),
                bookingDto.items() != null ? bookingDto.items().stream()
                        .map(BookingItemMapper.getInstance()::fromBookingItemDtoToBookingItem)
                        .collect(Collectors.toList()) : new ArrayList<>(),
                UserMapper.getInstance().fromUserDtoToUser(bookingDto.user())
        );
    }

    public BookingDto fromBookingEntityToBookingDto(BookingEntity bookingEntity) {
        if (bookingEntity == null) {
            return null;
        }
        return new BookingDto(
                bookingEntity.idBooking(),
                bookingEntity.total_price(),
                bookingEntity.items() != null ? bookingEntity.items().stream()
                        .map(BookingItemMapper.getInstance()::fromBookingItemEntityToBookingItemDto)
                        .collect(Collectors.toList()) : new ArrayList<>(),
                UserMapper.getInstance().fromUserEntityToUserDto(bookingEntity.user())
        );
    }

    public BookingEntity fromBookingDtoToBookingEntity(BookingDto bookingDto) {
        if (bookingDto == null) {
            return null;
        }
        return new BookingEntity(
                bookingDto.idBooking(),
                bookingDto.total_price(),
                bookingDto.items() != null ? bookingDto.items().stream()
                        .map(item -> BookingItemMapper.getInstance().fromBookingItemDtoToBookingItemEntity(item))
                        .collect(Collectors.toList()) : new ArrayList<>(),
                UserMapper.getInstance().fromUserDtoToUserEntity(bookingDto.user())
        );
    }

    public BookingEntity fromBookingJpaEntityToBookingEntity(BookingJpaEntity bookingJpaEntity) {
        if (bookingJpaEntity == null) {
            return null;
        }
        return new BookingEntity(
                bookingJpaEntity.getIdBooking(),
                bookingJpaEntity.getTotalPrice(),
                bookingJpaEntity.getBookingItems() != null ? bookingJpaEntity.getBookingItems().stream()
                        .map(BookingItemMapper.getInstance()::fromBookingItemJpaEntityToBookingItemEntity)
                        .collect(Collectors.toList()) : new ArrayList<>(),
                UserMapper.getInstance().fromUserJpaEntityToUserEntity(bookingJpaEntity.getUser())
        );
    }

    public BookingJpaEntity fromBookingEntityToBookingJpaEntity(BookingEntity bookingEntity) {
        if (bookingEntity == null) {
            return null;
        }
        BookingJpaEntity jpaEntity = new BookingJpaEntity();
        jpaEntity.setIdBooking(bookingEntity.idBooking());
        jpaEntity.setTotalPrice(bookingEntity.total_price());
        jpaEntity.setUser(UserMapper.getInstance().fromUserEntityToUserJpaEntity(bookingEntity.user()));
        return jpaEntity;
    }

    public BookingDetailResponse fromBookingDtoToBookingDetailResponse(BookingDto bookingDto) {
        if (bookingDto == null) {
            return null;
        }
        return new BookingDetailResponse(
                bookingDto.idBooking(),
                bookingDto.total_price(),
                bookingDto.user() != null ? bookingDto.user().idUser() : null,
                bookingDto.items() != null ? bookingDto.items().stream()
                        .map(BookingItemMapper.getInstance()::fromBookingItemDtoToBookingItemDetailResponse)
                        .collect(Collectors.toList()) : new ArrayList<>()
        );
    }

    public BookingDto fromBookingInsertRequestToBookingDto(BookingInsertRequest request) {
        if (request == null) {
            return null;
        }
        return new BookingDto(
                null,
                0.0,
                request.items() != null ? request.items().stream()
                        .map(BookingItemMapper.getInstance()::fromBookingItemInsertRequestToBookingItemDto)
                        .collect(Collectors.toList()) : new ArrayList<>(),
                new UserDto(request.idUser(), null, null, null, null, null, null, null, null)
        );
    }
}

