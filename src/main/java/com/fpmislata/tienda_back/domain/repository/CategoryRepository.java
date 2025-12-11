package com.fpmislata.tienda_back.domain.repository;

import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<CategoryDto> findAll();
    Optional<CategoryDto> findCategoryById(String id_category);
    void delete(String id_category);
    CategoryDto save(CategoryDto categoryDto);
    CategoryDto getById(String id_category);
}
