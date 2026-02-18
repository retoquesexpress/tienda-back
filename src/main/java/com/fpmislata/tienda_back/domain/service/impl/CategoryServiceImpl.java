package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.domain.repository.CategoryRepository;
import com.fpmislata.tienda_back.domain.repository.entity.CategoryEntity;
import com.fpmislata.tienda_back.domain.service.CategoryService;
import com.fpmislata.tienda_back.domain.service.dto.BookingDto;
import com.fpmislata.tienda_back.domain.service.dto.BookingItemDto;
import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;
import com.fpmislata.tienda_back.exception.ResourceNotFoundException;
import com.fpmislata.tienda_back.mapper.BookingItemMapper;
import com.fpmislata.tienda_back.mapper.BookingMapper;
import com.fpmislata.tienda_back.mapper.CategoryMapper;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper.getInstance()::fromCategoryEntityToCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDto> findCategoryById(Integer idCategory) {

        Optional<CategoryDto> category = categoryRepository.findCategoryById(idCategory).map(CategoryMapper.getInstance()::fromCategoryEntityToCategoryDto);
        if (category.isPresent()) {
            return category;
        } else {
            throw new ResourceNotFoundException("category not found");
        }
    }


    @Transactional
    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        return CategoryMapper.getInstance().fromCategoryEntityToCategoryDto(categoryRepository.create(CategoryMapper.getInstance().fromCategoryDtoToCategoryEntity(categoryDto)));
    }


    @Transactional
    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        Optional<CategoryEntity> category = categoryRepository.findCategoryById(categoryDto.idCategory());
        if (category.isPresent()) {
            return CategoryMapper.getInstance().fromCategoryEntityToCategoryDto(categoryRepository.update(category.get()));
        } else {
            throw new ResourceNotFoundException("category does not exists");
        }
    }

    @Transactional
    @Override
    public void delete(Integer idCategory) {
        Optional<CategoryEntity> category = categoryRepository.findCategoryById(idCategory);
        if (category.isPresent()) {
            categoryRepository.delete(idCategory);
        } else {
            throw new ResourceNotFoundException("category does not exists");
        }
    }

    @Override
    public CategoryDto getById(Integer idCategory) {
        Optional<CategoryEntity> category = categoryRepository.findCategoryById(idCategory);
        if (category.isEmpty()) {
            throw new ResourceNotFoundException("category not found");
        }
        return category.map(CategoryMapper.getInstance()::fromCategoryEntityToCategoryDto).get();
    }
}
