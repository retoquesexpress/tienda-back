package com.fpmislata.tienda_back.controller.webModel.request;

public record ServiceInsertRequest(
                String name,
                String description,
                double price,
                String pictureUrl,
                CategoryInsertRequest category) {
}
