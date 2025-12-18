package com.fpmislata.tienda_back.domain.service;

import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDto> findAll();

    Optional<CategoryDto> findCategoryById(Integer idCategory);

    CategoryDto create(CategoryDto categoryDto);

    CategoryDto update(CategoryDto categoryDto);

    void delete(Integer idCategory);

    CategoryDto getById(Integer idCategory);
}
