package com.fpmislata.tienda_back.domain.service;

import com.fpmislata.tienda_back.domain.model.BookingItem;
import com.fpmislata.tienda_back.domain.service.dto.BookingDto;
import com.fpmislata.tienda_back.domain.service.dto.BookingItemDto;

import java.util.List;

public interface BookingService {
    List<BookingDto> findAll();

    List<BookingDto> findAllBookingsByUserWhenBookingDateIsFuture(Integer idUser);

    BookingDto findById(Integer id);

    BookingDto getById(Integer id);

    BookingDto save(BookingDto bookingDto);

    void delete(Integer id);

}
