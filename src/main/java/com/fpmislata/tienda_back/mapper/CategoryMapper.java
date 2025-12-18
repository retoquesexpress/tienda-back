package com.fpmislata.tienda_back.mapper;

import com.fpmislata.tienda_back.controller.webModel.request.CategoryInsertRequest;
import com.fpmislata.tienda_back.controller.webModel.request.CategoryUpdateRequest;
import com.fpmislata.tienda_back.controller.webModel.response.CategoryDetailResponse;
import com.fpmislata.tienda_back.domain.model.Category;
import com.fpmislata.tienda_back.domain.repository.entity.CategoryEntity;
import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;

public class CategoryMapper {

    private static CategoryMapper INSTANCE;

    private CategoryMapper() {
    }

    public static CategoryMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CategoryMapper();
        }
        return INSTANCE;
    }

    public CategoryDto fromCategoryJpaEntityToCategoryDto(CategoryJpaEntity categoryJpaEntity) {
        if (categoryJpaEntity == null) {
            return null;
        }
        return new CategoryDto(
                categoryJpaEntity.getIdCategory(),
                categoryJpaEntity.getName());
    }

    public CategoryJpaEntity fromCategoryDtoToCategoryJpaEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        return new CategoryJpaEntity(
                categoryDto.idCategory(),
                categoryDto.name());
    }

    public CategoryDetailResponse fromCategoryDtoToCategoryDetailResponse(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        return new CategoryDetailResponse(
                categoryDto.idCategory(),
                categoryDto.name()

        );
    }

    public CategoryDto fromCategoryInsertRequestToCategoryDto(CategoryInsertRequest categoryInsertRequest) {
        if (categoryInsertRequest == null) {
            return null;
        }
        return new CategoryDto(
                categoryInsertRequest.idCategory(),
                categoryInsertRequest.name());
    }

    public CategoryDto fromCategoryUpdateRequestToCategoryDto(CategoryUpdateRequest categoryUpdateRequest) {
        if (categoryUpdateRequest == null) {
            return null;
        }
        return new CategoryDto(
                categoryUpdateRequest.idCategory(),
                categoryUpdateRequest.name());
    }
}
