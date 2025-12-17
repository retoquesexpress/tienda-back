package com.fpmislata.tienda_back.controller.webModel.request;

public record ServiceUpdateRequest(
                Integer id_service,
                String name,
                String description,
                double price,
                String pictureUrl,
                CategoryUpdateRequest category) {
}
