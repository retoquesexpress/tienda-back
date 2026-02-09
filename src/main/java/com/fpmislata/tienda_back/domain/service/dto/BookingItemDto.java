package com.fpmislata.tienda_back.domain.service.dto;

import java.time.LocalDateTime;

public record BookingItemDto(
        Integer idBookingItem,
        Integer idService,
        Integer quantity,
        LocalDateTime bookingDate,
        ServiceDto service) {
}
