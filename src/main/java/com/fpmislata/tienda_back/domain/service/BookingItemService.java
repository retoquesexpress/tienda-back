package com.fpmislata.tienda_back.domain.service;

import com.fpmislata.tienda_back.domain.service.dto.BookingItemDto;
import java.util.List;
import java.util.Map;

public interface BookingItemService {
    List<BookingItemDto> findAll();

    BookingItemDto findById(Integer id);

    BookingItemDto save(BookingItemDto bookingItemDto);

    void delete(Integer id);

}
