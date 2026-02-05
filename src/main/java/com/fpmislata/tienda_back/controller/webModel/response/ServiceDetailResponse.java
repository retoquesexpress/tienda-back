package com.fpmislata.tienda_back.controller.webModel.response;

public record ServiceDetailResponse(
                Integer idService,
                String name,
                String description,
                double price,
                String pictureUrl,
                CategoryDetailResponse category) {
}
