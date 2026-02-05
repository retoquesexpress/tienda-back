package com.fpmislata.tienda_back.controller.webModel.request;

import java.time.LocalDate;

public record BookingItemInsertRequest(
        Integer idBooking,
        Integer idService,
        Integer quantity,
        LocalDate bookingDate) {
}
