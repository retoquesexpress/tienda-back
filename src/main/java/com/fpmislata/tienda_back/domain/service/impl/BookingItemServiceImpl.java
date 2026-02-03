package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.domain.model.BookingItem;
import com.fpmislata.tienda_back.domain.repository.BookingItemRepository;
import com.fpmislata.tienda_back.domain.service.BookingItemService;
import com.fpmislata.tienda_back.domain.service.dto.BookingItemDto;
import com.fpmislata.tienda_back.mapper.BookingItemMapper;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookingItemServiceImpl implements BookingItemService {

    private final BookingItemRepository bookingItemRepository;

    public BookingItemServiceImpl(BookingItemRepository bookingItemRepository) {
        this.bookingItemRepository = bookingItemRepository;
    }

    @Override
    public List<BookingItemDto> findAll() {
        return bookingItemRepository.findAll().stream()
                .map(BookingItemMapper.getInstance()::fromBookingItemToBookingItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingItemDto findById(Integer id) {
        BookingItem bookingItem = bookingItemRepository.findById(id);
        return BookingItemMapper.getInstance().fromBookingItemToBookingItemDto(bookingItem);
    }

    @Override
    public BookingItemDto save(BookingItemDto bookingItemDto) {
        BookingItem bookingItem = BookingItemMapper.getInstance().fromBookingItemDtoToBookingItem(bookingItemDto);
        BookingItem savedBookingItem = bookingItemRepository.save(bookingItem);
        return BookingItemMapper.getInstance().fromBookingItemToBookingItemDto(savedBookingItem);
    }

    @Override
    public void delete(Integer id) {
        bookingItemRepository.delete(id);
    }
}
