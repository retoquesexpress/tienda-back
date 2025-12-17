package com.fpmislata.tienda_back.domain.service.dto;

import jakarta.validation.constraints.NotNull;

public record CategoryDto(
                Integer id_category,
                String name) {
}
