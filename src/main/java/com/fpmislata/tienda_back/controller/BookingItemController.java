package com.fpmislata.tienda_back.controller;

import com.fpmislata.tienda_back.controller.webModel.request.BookingItemInsertRequest;
import com.fpmislata.tienda_back.controller.webModel.response.BookingItemDetailResponse;
import com.fpmislata.tienda_back.domain.service.BookingItemService;
import com.fpmislata.tienda_back.domain.service.dto.BookingItemDto;
import com.fpmislata.tienda_back.mapper.BookingItemMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/booking-items")
public class BookingItemController {

    private final BookingItemService bookingItemService;

    public BookingItemController(BookingItemService bookingItemService) {
        this.bookingItemService = bookingItemService;
    }

    @GetMapping
    public ResponseEntity<List<BookingItemDetailResponse>> findAll() {
        List<BookingItemDetailResponse> responses = bookingItemService.findAll().stream()
                .map(BookingItemMapper.getInstance()::fromBookingItemDtoToBookingItemDetailResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingItemDetailResponse> findById(@PathVariable Integer id) {
        BookingItemDto dto = bookingItemService.findById(id);
        return new ResponseEntity<>(BookingItemMapper.getInstance().fromBookingItemDtoToBookingItemDetailResponse(dto),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookingItemDetailResponse> save(@RequestBody BookingItemInsertRequest request) {
        BookingItemDto dto = BookingItemMapper.getInstance().fromBookingItemInsertRequestToBookingItemDto(request);
        BookingItemDto savedDto = bookingItemService.save(dto);
        return new ResponseEntity<>(
                BookingItemMapper.getInstance().fromBookingItemDtoToBookingItemDetailResponse(savedDto),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        bookingItemService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
