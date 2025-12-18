package com.fpmislata.tienda_back.domain.repository.entity;

import java.util.Date;

public record CategoryEntity(
        Integer idCategory,
        String name) {
    public CategoryEntity(
            Integer idCategory,
            String name

    ) {
        this.idCategory = idCategory;
        this.name = name;
    }
}
