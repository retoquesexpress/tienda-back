package com.fpmislata.tienda_back.domain.service;

import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDto> findAll();

    Optional<CategoryDto> findCategoryById(Integer id_category);

    CategoryDto create(CategoryDto categoryDto);

    CategoryDto update(CategoryDto categoryDto);

    void delete(Integer id_category);

    CategoryDto getById(Integer id_category);
}
