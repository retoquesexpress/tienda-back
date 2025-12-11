package com.fpmislata.tienda_back.mapper;

import com.fpmislata.tienda_back.controller.webModel.response.CategoryDetailResponse;
import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;

public class CategoryMapper {

    private static CategoryMapper instance;

    private CategoryMapper() {
    }

    public static CategoryMapper getInstance() {
        if (instance == null) {
            instance = new CategoryMapper();
        }
        return instance;
    }

    public CategoryDto fromCategoryJpaEntityToCategoryDto(CategoryJpaEntity categoryJpaEntity) {
        if (categoryJpaEntity == null) {
            return null;
        }
        return new CategoryDto(
                categoryJpaEntity.getId_category(),
                categoryJpaEntity.getName()
        );
    }

    public CategoryJpaEntity fromCategoryDtoToCategoryJpaEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        return new CategoryJpaEntity(
                categoryDto.id_category(),
                categoryDto.name()
        );
    }

    public static CategoryDetailResponse fromCategoryDtoToCategoryDetailResponse(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        return new CategoryDetailResponse(
                categoryDto.id_category(),
                categoryDto.name()

        );
    }

    public static CategoryDto fromCategoryDetailResponseToCategoryDto(CategoryDetailResponse categoryDetailResponse) {
        if (categoryDetailResponse == null) {
            return null;
        }
        return new CategoryDto(
                categoryDetailResponse.id_category(),
                categoryDetailResponse.name()
        );
    }
}
