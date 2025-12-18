package com.fpmislata.tienda_back.domain.repository;

import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<CategoryDto> findAll();

    Optional<CategoryDto> findCategoryById(Integer id_category);

    void delete(Integer id_category);

    CategoryDto create(CategoryDto categoryDto);

    CategoryDto update(CategoryDto categoryDto);

    CategoryDto getById(Integer id_category);
}
