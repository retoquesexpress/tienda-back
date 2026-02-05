package com.fpmislata.tienda_back.controller.webModel.response;

import java.time.LocalDate;

public record BookingItemDetailResponse(
        Integer idBookingItem,
        Integer idBooking,
        Integer idService,
        Integer quantity,
        LocalDate bookingDate,
        String serviceName,
        String categoryName) {
}
