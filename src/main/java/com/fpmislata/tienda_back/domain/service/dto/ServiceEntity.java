package com.fpmislata.tienda_back.domain.service.dto;


import jakarta.validation.constraints.NotNull;

public record ServiceEntity(
        @NotNull
        String id_service,
        String name,
        String description,
        @NotNull
        double price,
        String pictureUrl,
        @NotNull
        CategoryDto category
) {
        public ServiceEntity {
                if (id_service == null) {
                        throw new NullPointerException("id_service must not be null");
                }
                if (Double.isNaN(price)) {
                        throw new NullPointerException("price must not be null");
                }
                if (category.id_category() == null) {
                        throw new NullPointerException("category id must not be null");
                }
        }
}
