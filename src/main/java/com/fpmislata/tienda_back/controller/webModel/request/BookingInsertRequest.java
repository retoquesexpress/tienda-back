package com.fpmislata.tienda_back.controller.webModel.request;

import java.util.List;

public record BookingInsertRequest(
        Integer idUser,
        List<BookingItemInsertRequest> items
) {
}
