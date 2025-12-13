package com.fpmislata.tienda_back.controller.webModel.response;

public record ServiceSummaryResponse(
        String id_service,
        String name,
        double price
) {
}
