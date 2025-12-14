package com.fpmislata.tienda_back.controller.webModel.request;


import com.fpmislata.tienda_back.domain.model.Category;

public record ServiceInsertRequest(
        String id_service,
        String name,
        String description,
        double price,
        String pictureUrl,
        CategoryInsertRequest categoryInsertRequest
) {
}
