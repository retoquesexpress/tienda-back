package com.fpmislata.tienda_back.controller.webModel.response;

import java.time.LocalDateTime;

public record BookingItemDetailResponse(
        Integer idBookingItem,
        Integer idService,
        Integer quantity,
        LocalDateTime bookingDate,
        String serviceName,
        String categoryName,
        String pictureUrl,
        Integer idCategory) {
}
