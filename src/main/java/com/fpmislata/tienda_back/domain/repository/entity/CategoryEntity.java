package com.fpmislata.tienda_back.domain.repository.entity;

import java.util.Date;

public record CategoryEntity(
        String id_category,
        String name
) {
    public CategoryEntity(
            String id_category,
            String name

    ) {
        this.id_category = id_category;
        this.name = name;
    }
}
