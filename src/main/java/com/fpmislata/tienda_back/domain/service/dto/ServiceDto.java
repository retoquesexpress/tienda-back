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

}
