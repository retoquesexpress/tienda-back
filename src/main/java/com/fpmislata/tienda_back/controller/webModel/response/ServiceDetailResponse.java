package com.fpmislata.tienda_back.controller.webModel.response;

import com.fpmislata.tienda_back.domain.model.Category;

public record ServiceDetailResponse(
                Integer id_service,
                String name,
                String description,
                double price,
                String pictureUrl,
                CategoryDetailResponse category) {
}
