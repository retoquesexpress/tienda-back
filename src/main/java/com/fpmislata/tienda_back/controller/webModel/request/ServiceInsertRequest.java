package com.fpmislata.tienda_back.controller.webModel.request;


public record ServiceInsertRequest(
        String id_service,
        String name,
        String description,
        double price,
        String pictureUrl
) {
}
