package com.fpmislata.tienda_back.persistence.repository;

import com.fpmislata.tienda_back.domain.repository.CategoryRepository;
import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;
import com.fpmislata.tienda_back.mapper.CategoryMapper;
import com.fpmislata.tienda_back.persistence.dao.jpa.CategoryJpaDao;

import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryJpaDao categoryJpaDao;
    public CategoryRepositoryImpl(CategoryJpaDao categoryJpaDao) {
        this.categoryJpaDao = categoryJpaDao;
    }


    @Override
    public List<CategoryDto> findAll() {
        return categoryJpaDao.findAll().stream().map(CategoryMapper.getInstance()::fromCategoryJpaEntityToCategoryDto).toList();
    }

    @Override
    public Optional<CategoryDto> findCategoryById(String id_category) {
        return categoryJpaDao.findCategoryById(id_category).map(CategoryMapper.getInstance()::fromCategoryJpaEntityToCategoryDto);
    }

    @Override
    public void delete(String id_category) {
        categoryJpaDao.delete(id_category);
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        if (categoryDto.id_category() == null) {
            return CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryDto(
                    categoryJpaDao.insert(CategoryMapper.getInstance().fromCategoryDtoToCategoryJpaEntity(categoryDto)));
        } else {
            return CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryDto(
                    categoryJpaDao.update(CategoryMapper.getInstance().fromCategoryDtoToCategoryJpaEntity(categoryDto)));
        }    }

    @Override
    public CategoryDto getById(String id_category) {
        return categoryJpaDao.findCategoryById(id_category)
                .map(CategoryMapper.getInstance()::fromCategoryJpaEntityToCategoryDto)
                .orElse(null);
    }
}
