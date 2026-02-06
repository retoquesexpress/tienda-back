package com.fpmislata.tienda_back.controller.webModel.response;

import java.util.List;

public record BookingDetailResponse(
        Integer idBooking,
        Double totalPrice,
        Integer idUser,
        List<BookingItemDetailResponse> items
) {
}
