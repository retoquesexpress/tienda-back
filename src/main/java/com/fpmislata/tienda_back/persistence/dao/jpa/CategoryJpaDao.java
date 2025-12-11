package com.fpmislata.tienda_back.persistence.dao.jpa;

import com.fpmislata.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryJpaDao {
    List<CategoryJpaEntity> findAll();
    Optional<CategoryJpaEntity> findCategoryById(String id_category);
    CategoryJpaEntity update (CategoryJpaEntity categoryDto);
    void delete(String id_category);
    CategoryJpaEntity insert (CategoryJpaEntity categoryDto);
    CategoryJpaEntity getById(String id_category);
}
