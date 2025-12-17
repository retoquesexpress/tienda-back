package com.fpmislata.tienda_back.persistence.dao.jpa;

import com.fpmislata.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryJpaDao {
    List<CategoryJpaEntity> findAll();

    Optional<CategoryJpaEntity> findCategoryById(Integer id_category);

    CategoryJpaEntity update(CategoryJpaEntity categoryDto);

    void delete(Integer id_category);

    CategoryJpaEntity insert(CategoryJpaEntity categoryDto);

    CategoryJpaEntity getById(Integer id_category);
}
