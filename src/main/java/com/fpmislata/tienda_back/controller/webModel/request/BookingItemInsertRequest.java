package com.fpmislata.tienda_back.controller.webModel.request;

import java.time.LocalDateTime;

public record BookingItemInsertRequest(
                Integer idBooking,
                Integer idService,
                Integer quantity,
                LocalDateTime bookingDate) {
}
