package com.fpmislata.tienda_back.domain.repository.entity;

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
