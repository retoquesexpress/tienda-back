package com.fpmislata.tienda_back.domain.repository.entity;

import com.fpmislata.tienda_back.domain.model.Category;

public record ServiceEntity(
        String idService,
        String name,
        String description,
        double price,
        String pictureUrl,
        CategoryEntity category) {
    public ServiceEntity(
            String idService,
            String name,
            String description,
            double price,
            String pictureUrl,
            CategoryEntity category) {
        this.idService = idService;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.category = category;
    }
}
