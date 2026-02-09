package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.domain.repository.BookingRepository;
import com.fpmislata.tienda_back.domain.service.BookingService;
import com.fpmislata.tienda_back.domain.service.dto.BookingDto;
import com.fpmislata.tienda_back.exception.ResourceNotFoundException;
import com.fpmislata.tienda_back.mapper.BookingMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<BookingDto> findAll() {
        return bookingRepository.findAll().stream()
                .map(BookingMapper.getInstance()::fromBookingEntityToBookingDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> findAllBookingsByUserWhenBookingDateIsFuture(Integer idUser) {
        return bookingRepository.findAllBookingsByUserWhenBookingDateIsFuture(idUser).stream()
                .map(BookingMapper.getInstance()::fromBookingEntityToBookingDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDto findById(Integer id) {
        return bookingRepository.findById(id)
                .map(BookingMapper.getInstance()::fromBookingEntityToBookingDto)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
    }

    @Override
    public BookingDto getById(Integer id) {
        return findById(id);
    }

    @Override
    public BookingDto save(BookingDto bookingDto) {
        var entity = BookingMapper.getInstance().fromBookingDtoToBookingEntity(bookingDto);
        var savedEntity = bookingRepository.save(entity);
        return BookingMapper.getInstance().fromBookingEntityToBookingDto(savedEntity);
    }

    @Override
    public void delete(Integer id) {
        bookingRepository.delete(id);
    }
}
