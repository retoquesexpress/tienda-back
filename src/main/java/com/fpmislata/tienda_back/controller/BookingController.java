package com.fpmislata.tienda_back.controller;

import com.fpmislata.tienda_back.controller.webModel.request.BookingInsertRequest;
import com.fpmislata.tienda_back.controller.webModel.response.BookingDetailResponse;
import com.fpmislata.tienda_back.domain.service.BookingService;
import com.fpmislata.tienda_back.domain.service.dto.BookingDto;
import com.fpmislata.tienda_back.mapper.BookingMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<BookingDetailResponse>> findAll() {
        List<BookingDetailResponse> response = bookingService.findAll().stream()
                .map(BookingMapper.getInstance()::fromBookingDtoToBookingDetailResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDetailResponse> findById(@PathVariable Integer id) {
        BookingDto bookingDto = bookingService.findById(id);
        return new ResponseEntity<>(BookingMapper.getInstance().fromBookingDtoToBookingDetailResponse(bookingDto), HttpStatus.OK);
    }

    @GetMapping("/booking/{idUser}")
    public ResponseEntity<List<BookingDetailResponse>> findFutureBookingsByUser(@PathVariable Integer idUser) {
        List<BookingDetailResponse> response = bookingService.findAllBookingsByUserWhenBookingDateIsFuture(idUser).stream()
                .map(BookingMapper.getInstance()::fromBookingDtoToBookingDetailResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<BookingDetailResponse> save(@RequestBody BookingInsertRequest request) {
        BookingDto bookingDto = BookingMapper.getInstance().fromBookingInsertRequestToBookingDto(request);
        BookingDto savedBooking = bookingService.save(bookingDto);
        return new ResponseEntity<>(BookingMapper.getInstance().fromBookingDtoToBookingDetailResponse(savedBooking), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        bookingService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
