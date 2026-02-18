package com.fpmislata.tienda_back.persistence.repository;

import com.fpmislata.tienda_back.domain.repository.CategoryRepository;
import com.fpmislata.tienda_back.domain.repository.entity.CategoryEntity;
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
    public List<CategoryEntity> findAll() {
        return categoryJpaDao.findAll().stream().map(CategoryMapper.getInstance()::fromCategoryJpaEntityToCategoryEntity)
                .toList();
    }

    @Override
    public Optional<CategoryEntity> findCategoryById(Integer id_category) {
        return categoryJpaDao.findCategoryById(id_category)
                .map(CategoryMapper.getInstance()::fromCategoryJpaEntityToCategoryEntity);
    }

    @Override
    public void delete(Integer id_category) {
        categoryJpaDao.delete(id_category);
    }

    @Override
    public CategoryEntity create(CategoryEntity categoryEntity) {
        CategoryJpaEntity entity = CategoryMapper.getInstance().fromCategoryEntityToCategoryJpaEntity(categoryEntity);
        entity.setIdCategory(null);
        CategoryJpaEntity createdEntity = categoryJpaDao.insert(entity);
        return CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryEntity(createdEntity);
    }

    @Override
    public CategoryEntity update(CategoryEntity categoryEntity) {
        Integer categoryId = categoryEntity.idCategory();
        CategoryJpaEntity existingEntity = categoryJpaDao.findCategoryById(categoryId).orElseThrow(
                () -> new EntityNotFoundException("Category con ID " + categoryId + " no encontrado para actualizar."));
        existingEntity.setIdCategory(categoryId);
        existingEntity.setName(categoryEntity.name());
        CategoryJpaEntity updatedEntity = categoryJpaDao.update(existingEntity);
        return CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryEntity(updatedEntity);

    }

    @Override
    public CategoryEntity getById(Integer id_category) {
        return categoryJpaDao.findCategoryById(id_category)
                .map(CategoryMapper.getInstance()::fromCategoryJpaEntityToCategoryEntity)
                .orElse(null);
    }
}
