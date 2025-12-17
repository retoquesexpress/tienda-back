package com.fpmislata.tienda_back.domain.repository.entity;

import java.util.Date;

public record CategoryEntity(
        Integer id_category,
        String name) {
    public CategoryEntity(
            Integer id_category,
            String name

    ) {
        this.id_category = id_category;
        this.name = name;
    }
}
