package com.fpmislata.tienda_back.domain.service.dto;

import java.time.LocalDate;

public record BookingItemDto(
                Integer idBookingItem,
                Integer idBooking,
                Integer idService,
                Integer quantity,
                LocalDate bookingDate,
                ServiceDto service) {
}
