package com.fpmislata.tienda_back.domain.repository.entity;

import com.fpmislata.tienda_back.domain.model.Category;

public record ServiceEntity(
        String id_service,
        String name,
        String description,
        double price,
        String pictureUrl,
        CategoryEntity category
) {
    public ServiceEntity(
            String id_service,
            String name,
            String description,
            double price,
            String pictureUrl,
            CategoryEntity category
    ) {
        this.id_service = id_service;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.category = category;
    }
}
