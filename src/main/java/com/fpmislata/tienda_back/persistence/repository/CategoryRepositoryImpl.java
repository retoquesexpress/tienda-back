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
        entity.setId_category(null); // Ensure ID is null for new entity creation
        CategoryJpaEntity createdEntity = categoryJpaDao.insert(entity);
        return CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryDto(createdEntity);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        Integer categoryId = categoryDto.id_category();
        CategoryJpaEntity existingEntity = categoryJpaDao.findCategoryById(categoryId).orElseThrow(
                () -> new EntityNotFoundException("Category con ID " + categoryId + " no encontrado para actualizar."));
        existingEntity.setId_category(categoryId);
        existingEntity.setName(categoryDto.name());
        CategoryJpaEntity updatedEntity = categoryJpaDao.update(existingEntity);
        return CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryDto(updatedEntity);

    }

    // o separa los metodos de insertar y actualizar
    // o si no existe insertar y si existe actualizar
    /*
     * @Override
     * public CategoryDto save(CategoryDto categoryDto) {
     * if (categoryDto.id_category() == null) {
     * return CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryDto(
     * categoryJpaDao.insert(CategoryMapper.getInstance().
     * fromCategoryDtoToCategoryJpaEntity(categoryDto)));
     * } else {
     * return CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryDto(
     * categoryJpaDao.update(CategoryMapper.getInstance().
     * fromCategoryDtoToCategoryJpaEntity(categoryDto)));
     * }
     * }
     */

    @Override
    public CategoryDto getById(Integer id_category) {
        return categoryJpaDao.findCategoryById(id_category)
                .map(CategoryMapper.getInstance()::fromCategoryJpaEntityToCategoryDto)
                .orElse(null);
    }
}
