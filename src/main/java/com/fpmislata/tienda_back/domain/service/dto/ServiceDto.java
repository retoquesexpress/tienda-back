package com.fpmislata.tienda_back.domain.service.dto;


import jakarta.validation.constraints.NotNull;

public record ServiceDto(
        @NotNull
        String id_service,
        String name,
        String description,
        @NotNull
        double price,
        String pictureUrl
) {
        public ServiceDto {
                if (id_service == null) {
                        throw new NullPointerException("id_service must not be null");
                }
                if (Double.isNaN(price)) {
                        throw new NullPointerException("price must not be null");
                }
        }
}
