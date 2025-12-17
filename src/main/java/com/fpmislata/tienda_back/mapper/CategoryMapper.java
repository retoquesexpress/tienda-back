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

    public CategoryDto fromCategoryEntityToCategoryDto(CategoryEntity category) {
        if (category == null) {
            return null;
        }
        return new CategoryDto(
                category.id_category(),
                category.name());
    }

    public CategoryDto fromCategoryJpaEntityToCategoryDto(CategoryJpaEntity categoryJpaEntity) {
        if (categoryJpaEntity == null) {
            return null;
        }
        return new CategoryDto(
                categoryJpaEntity.getId_category(),
                categoryJpaEntity.getName());
    }

    public CategoryJpaEntity fromCategoryDtoToCategoryJpaEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        return new CategoryJpaEntity(
                categoryDto.id_category(),
                categoryDto.name());
    }

    public CategoryDetailResponse fromCategoryDtoToCategoryDetailResponse(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        return new CategoryDetailResponse(
                categoryDto.id_category(),
                categoryDto.name()

        );
    }

    public CategoryDto fromCategoryDetailResponseToCategoryDto(CategoryDetailResponse categoryDetailResponse) {
        if (categoryDetailResponse == null) {
            return null;
        }
        return new CategoryDto(
                categoryDetailResponse.id_category(),
                categoryDetailResponse.name());
    }

    public CategoryEntity fromCategoryDtoToCategoryEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        return new CategoryEntity(
                categoryDto.id_category(),
                categoryDto.name());
    }

    public CategoryDto fromCategoryInsertRequestToCategoryDto(CategoryInsertRequest categoryInsertRequest) {
        if (categoryInsertRequest == null) {
            return null;
        }
        return new CategoryDto(
                categoryInsertRequest.id_category(),
                categoryInsertRequest.name());
    }

    public CategoryDto fromCategoryUpdateRequestToCategoryDto(CategoryUpdateRequest categoryUpdateRequest) {
        if (categoryUpdateRequest == null) {
            return null;
        }
        return new CategoryDto(
                categoryUpdateRequest.id_category(),
                categoryUpdateRequest.name());
    }
}
