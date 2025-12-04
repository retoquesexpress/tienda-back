package com.fpmislata.tienda_back.domain.repository.entity;

public record ServiceEntity(
        String id_service,
        String name,
        String description,
        double price,
        String pictureUrl
) {
    public ServiceEntity(
            String id_service,
            String name,
            String description,
            double price,
            String pictureUrl
    ) {
        this.id_service = id_service;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pictureUrl = pictureUrl;
    }
}
