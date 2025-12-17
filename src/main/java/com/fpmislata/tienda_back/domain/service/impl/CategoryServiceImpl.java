package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.domain.repository.CategoryRepository;
import com.fpmislata.tienda_back.domain.service.CategoryService;
import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;
import com.fpmislata.tienda_back.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<CategoryDto> findCategoryById(Integer id_category) {
        Optional<CategoryDto> category = categoryRepository.findCategoryById(id_category);
        if (category.isPresent()) {
            return category;
        } else {
            throw new ResourceNotFoundException("category not found");
        }
    }


    @Transactional
    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        return categoryRepository.create(categoryDto);
    }

    @Transactional
    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        Optional<CategoryDto> category = categoryRepository.findCategoryById(categoryDto.id_category());
        if (category.isPresent()) {
            return categoryRepository.update(categoryDto);
        } else {
            throw new ResourceNotFoundException("category does not exists");
        }
    }

    @Transactional
    @Override
    public void delete(Integer id_category) {
        Optional<CategoryDto> category = categoryRepository.findCategoryById(id_category);
        if (category.isPresent()) {
            categoryRepository.delete(id_category);
        } else {
            throw new ResourceNotFoundException("category does not exists");
        }
    }

    @Override
    public CategoryDto getById(Integer id_category) {
        Optional<CategoryDto> category = categoryRepository.findCategoryById(id_category);
        if (category.isEmpty()) {
            throw new ResourceNotFoundException("category not found");
        }
        return category.get();
    }
}
