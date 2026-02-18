package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.domain.model.BookingItem;
import com.fpmislata.tienda_back.domain.repository.BookingItemRepository;
import com.fpmislata.tienda_back.domain.repository.entity.BookingItemEntity;
import com.fpmislata.tienda_back.domain.service.BookingItemService;
import com.fpmislata.tienda_back.domain.service.dto.BookingItemDto;
import com.fpmislata.tienda_back.exception.ResourceNotFoundException;
import com.fpmislata.tienda_back.mapper.BookingItemMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class BookingItemServiceImpl implements BookingItemService {

    private final BookingItemRepository bookingItemRepository;

    public BookingItemServiceImpl(BookingItemRepository bookingItemRepository) {
        this.bookingItemRepository = bookingItemRepository;
    }

    @Override
    public List<BookingItemDto> findAll() {
        return bookingItemRepository.findAll().stream()
                .map(BookingItemMapper.getInstance()::fromBookingItemEntityToBookingItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingItemDto findById(Integer id) {
        BookingItem bookingItem = bookingItemRepository.findById(id)
                .map(BookingItemMapper.getInstance()::fromBookingItemEntityToBookingItem)
                .orElseThrow(() -> new ResourceNotFoundException("BookingItem not found"));
        return BookingItemMapper.getInstance().fromBookingItemToBookingItemDto(bookingItem);
    }

    @Override
    public BookingItemDto save(BookingItemDto bookingItemDto) {
        BookingItem bookingItem = BookingItemMapper.getInstance().fromBookingItemDtoToBookingItem(bookingItemDto);
        BookingItemEntity savedBookingItem = bookingItemRepository.save(BookingItemMapper.getInstance().fromBookingItemToBookingItemEntity(bookingItem));
        return BookingItemMapper.getInstance().fromBookingItemEntityToBookingItemDto(savedBookingItem);
    }

    @Override
    public void delete(Integer id) {
        bookingItemRepository.delete(id);
    }

    @Override
    public void increaseQuantityById(Integer id) {
        bookingItemRepository.increaseQuantityById(id);
    }

    @Override
    public void decreaseQuantityById(Integer id) {
        bookingItemRepository.decreaseQuantityById(id);
    }
}
