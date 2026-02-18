package com.fpmislata.tienda_back.domain.repository;

import com.fpmislata.tienda_back.domain.repository.entity.CategoryEntity;
import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<CategoryEntity> findAll();

    Optional<CategoryEntity> findCategoryById(Integer idCategory);

    void delete(Integer id_category);

    CategoryEntity create(CategoryEntity categoryEntity);

    CategoryEntity update(CategoryEntity categoryEntity);

    CategoryEntity getById(Integer idCategory);
}
