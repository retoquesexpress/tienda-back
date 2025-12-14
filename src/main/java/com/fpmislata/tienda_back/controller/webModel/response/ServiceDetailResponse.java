package com.fpmislata.tienda_back.controller.webModel.response;

public record ServiceDetailResponse (
        String id_service,
        String name,
        String description,
        double price,
        String pictureUrl,
        CategoryDetailResponse categoryDetailResponse
) {
}
