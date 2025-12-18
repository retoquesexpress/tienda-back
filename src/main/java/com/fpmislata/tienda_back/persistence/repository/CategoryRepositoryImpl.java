package com.fpmislata.tienda_back.persistence.repository;

import com.fpmislata.tienda_back.domain.repository.CategoryRepository;
import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;
import com.fpmislata.tienda_back.mapper.CategoryMapper;
import com.fpmislata.tienda_back.mapper.ServiceMapper;
import com.fpmislata.tienda_back.persistence.dao.jpa.CategoryJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.ServiceJpaEntity;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryJpaDao categoryJpaDao;

    public CategoryRepositoryImpl(CategoryJpaDao categoryJpaDao) {
        this.categoryJpaDao = categoryJpaDao;
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryJpaDao.findAll().stream().map(CategoryMapper.getInstance()::fromCategoryJpaEntityToCategoryDto)
                .toList();
    }

    @Override
    public Optional<CategoryDto> findCategoryById(Integer id_category) {
        return categoryJpaDao.findCategoryById(id_category)
                .map(CategoryMapper.getInstance()::fromCategoryJpaEntityToCategoryDto);
    }

    @Override
    public void delete(Integer id_category) {
        categoryJpaDao.delete(id_category);
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        CategoryJpaEntity entity = CategoryMapper.getInstance().fromCategoryDtoToCategoryJpaEntity(categoryDto);
        entity.setIdCategory(null); // Ensure ID is null for new entity creation
        CategoryJpaEntity createdEntity = categoryJpaDao.insert(entity);
        return CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryDto(createdEntity);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        Integer categoryId = categoryDto.idCategory();
        CategoryJpaEntity existingEntity = categoryJpaDao.findCategoryById(categoryId).orElseThrow(
                () -> new EntityNotFoundException("Category con ID " + categoryId + " no encontrado para actualizar."));
        existingEntity.setIdCategory(categoryId);
        existingEntity.setName(categoryDto.name());
        CategoryJpaEntity updatedEntity = categoryJpaDao.update(existingEntity);
        return CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryDto(updatedEntity);

    }

    @Override
    public CategoryDto getById(Integer id_category) {
        return categoryJpaDao.findCategoryById(id_category)
                .map(CategoryMapper.getInstance()::fromCategoryJpaEntityToCategoryDto)
                .orElse(null);
    }
}
