package com.fpmislata.tienda_back.mapper;

import com.fpmislata.tienda_back.controller.webModel.request.BookingItemInsertRequest;
import com.fpmislata.tienda_back.controller.webModel.response.BookingItemDetailResponse;
import com.fpmislata.tienda_back.domain.model.BookingItem;
import com.fpmislata.tienda_back.domain.service.dto.BookingItemDto;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.BookingItemJpaEntity;

public class BookingItemMapper {
    private static BookingItemMapper INSTANCE;

    private BookingItemMapper() {
    }

    public static BookingItemMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookingItemMapper();
        }
        return INSTANCE;
    }

    public BookingItemDto fromBookingItemJpaEntityToBookingItemDto(BookingItemJpaEntity bookingItemJpaEntity) {
        if (bookingItemJpaEntity == null) {
            return null;
        }
        return new BookingItemDto(
                bookingItemJpaEntity.getIdBookingItem(),
                bookingItemJpaEntity.getBooking().getIdBooking(),
                bookingItemJpaEntity.getService().getIdService(),
                bookingItemJpaEntity.getQuantity(),
                bookingItemJpaEntity.getBookingDate(),
                ServiceMapper.getInstance().fromServiceJpaEntityToServiceDto(bookingItemJpaEntity.getService()));
    }

    public BookingItemJpaEntity fromBookingItemDtoToBookingItemJpaEntity(BookingItemDto bookingItemDto) {
        if (bookingItemDto == null) {
            return null;
        }

        BookingItemJpaEntity bookingItemJpaEntity = new BookingItemJpaEntity();
        bookingItemJpaEntity.setIdBookingItem(bookingItemDto.idBookingItem());
        bookingItemJpaEntity.setQuantity(bookingItemDto.quantity());
        bookingItemJpaEntity.setBookingDate(bookingItemDto.bookingDate());

        return bookingItemJpaEntity;
    }

    public BookingItemDetailResponse fromBookingItemDtoToBookingItemDetailResponse(BookingItemDto bookingItemDto) {
        if (bookingItemDto == null) {
            return null;
        }
        return new BookingItemDetailResponse(
                bookingItemDto.idBookingItem(),
                bookingItemDto.idBooking(),
                bookingItemDto.idService(),
                bookingItemDto.quantity(),
                bookingItemDto.bookingDate(),
                bookingItemDto.service() != null ? bookingItemDto.service().name() : null,
                (bookingItemDto.service() != null && bookingItemDto.service().category() != null)
                        ? bookingItemDto.service().category().name()
                        : null);
    }

    public BookingItemDto fromBookingItemEntityToBookingItemDto(
            com.fpmislata.tienda_back.domain.repository.entity.BookingItemEntity bookingItemEntity) {
        if (bookingItemEntity == null) {
            return null;
        }
        return new BookingItemDto(
                bookingItemEntity.idBookingItem(),
                bookingItemEntity.idBooking(),
                bookingItemEntity.idService(),
                bookingItemEntity.quantity(),
                bookingItemEntity.bookingDate(),
                ServiceMapper.getInstance().fromServiceEntityToServiceDto(bookingItemEntity.service()));
    }

    public BookingItem fromBookingItemDtoToBookingItem(BookingItemDto bookingItemDto) {
        if (bookingItemDto == null) {
            return null;
        }
        return new BookingItem(
                bookingItemDto.idBookingItem(),
                bookingItemDto.quantity(),
                bookingItemDto.bookingDate(),
                ServiceMapper.getInstance().fromServiceDtoToService(bookingItemDto.service()));
    }

    public BookingItem fromBookingItemJpaEntityToBookingItem(BookingItemJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new BookingItem(
                entity.getIdBookingItem(),
                entity.getQuantity(),
                entity.getBookingDate(),
                ServiceMapper.getInstance().fromServiceJpaEntityToService(entity.getService()));
    }

    public com.fpmislata.tienda_back.domain.repository.entity.BookingItemEntity fromBookingItemJpaEntityToBookingItemEntity(
            BookingItemJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new com.fpmislata.tienda_back.domain.repository.entity.BookingItemEntity(
                entity.getIdBookingItem(),
                entity.getBooking().getIdBooking(),
                entity.getService().getIdService(),
                entity.getQuantity(),
                entity.getBookingDate(),
                ServiceMapper.getInstance().fromServiceJpaEntityToServiceEntity(entity.getService()));
    }

    public BookingItemJpaEntity fromBookingItemEntityToBookingItemJpaEntity(
            com.fpmislata.tienda_back.domain.repository.entity.BookingItemEntity bookingItemEntity) {
        if (bookingItemEntity == null) {
            return null;
        }
        BookingItemJpaEntity jpaEntity = new BookingItemJpaEntity();
        jpaEntity.setIdBookingItem(bookingItemEntity.idBookingItem());
        jpaEntity.setQuantity(bookingItemEntity.quantity());
        jpaEntity.setBookingDate(bookingItemEntity.bookingDate());

        if (bookingItemEntity.idBooking() != null) {
            com.fpmislata.tienda_back.persistence.dao.jpa.entity.BookingJpaEntity booking = new com.fpmislata.tienda_back.persistence.dao.jpa.entity.BookingJpaEntity();
            booking.setIdBooking(bookingItemEntity.idBooking());
            jpaEntity.setBooking(booking);
        }

        if (bookingItemEntity.idService() != null) {
            com.fpmislata.tienda_back.persistence.dao.jpa.entity.ServiceJpaEntity service = new com.fpmislata.tienda_back.persistence.dao.jpa.entity.ServiceJpaEntity();
            service.setIdService(bookingItemEntity.idService());
            jpaEntity.setService(service);
        }

        return jpaEntity;
    }

    public BookingItemDto fromBookingItemToBookingItemDto(BookingItem bookingItem) {
        if (bookingItem == null) {
            return null;
        }
        return new BookingItemDto(
                bookingItem.getIdBookingItem(),
                null, // No idBooking in domain model
                bookingItem.getService() != null ? bookingItem.getService().getId() : null,
                bookingItem.getQuantity(),
                bookingItem.getBookingDate(),
                ServiceMapper.getInstance().fromServiceToServiceDto(bookingItem.getService()));
    }

    public BookingItemDto fromBookingItemInsertRequestToBookingItemDto(BookingItemInsertRequest request) {
        if (request == null) {
            return null;
        }
        return new BookingItemDto(
                null,
                request.idBooking(),
                request.idService(),
                request.quantity(),
                request.bookingDate(),
                null);
    }

    public BookingItemJpaEntity fromBookingItemToBookingItemJpaEntity(BookingItem bookingItem) {
        if (bookingItem == null) {
            return null;
        }
        BookingItemJpaEntity entity = new BookingItemJpaEntity();
        entity.setIdBookingItem(bookingItem.getIdBookingItem());
        entity.setQuantity(bookingItem.getQuantity());
        entity.setBookingDate(bookingItem.getBookingDate());

        // Note: booking ID is not available in domain model to avoid loops
        if (bookingItem.getService() != null && bookingItem.getService().getId() != null) {
            com.fpmislata.tienda_back.persistence.dao.jpa.entity.ServiceJpaEntity service = new com.fpmislata.tienda_back.persistence.dao.jpa.entity.ServiceJpaEntity();
            service.setIdService(bookingItem.getService().getId());
            entity.setService(service);
        }

        return entity;
    }
}
