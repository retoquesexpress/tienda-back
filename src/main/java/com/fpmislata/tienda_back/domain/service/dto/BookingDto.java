package com.fpmislata.tienda_back.domain.service.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record BookingDto(
        @NotNull Integer idBooking,
        @NotNull double total_price,
        @NotNull List<BookingItemDto> items,
        @NotNull UserDto user
) {
}

