package com.fpmislata.tienda_back.domain.service.dto;

import jakarta.validation.constraints.NotNull;

public record ServiceDto(
                Integer idService,
                String name,
                String description,
                @NotNull double price,
                String pictureUrl,
                @NotNull CategoryDto category) {
        public ServiceDto {
                if (Double.isNaN(price)) {
                        throw new NullPointerException("price must not be null");
                }
                if (category.idCategory() == null) {
                        throw new NullPointerException("category id must not be null");
                }
        }
}
