package com.fpmislata.tienda_back.persistence.dao.jpa.impl;

import com.fpmislata.tienda_back.persistence.dao.jpa.CategoryJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class CategoryJpaDaoImpl implements CategoryJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CategoryJpaEntity> findAll() {
        return entityManager
                .createQuery("SELECT c FROM CategoryJpaEntity c ORDER BY c.idCategory", CategoryJpaEntity.class)
                .getResultList();
    }

    @Override
    public Optional<CategoryJpaEntity> findCategoryById(Integer idCategory) {
        if (idCategory == null) {
            return Optional.empty();
        }
        CategoryJpaEntity categoryJpaEntity = entityManager.find(CategoryJpaEntity.class, idCategory);
        return Optional.ofNullable(categoryJpaEntity);
    }

    @Override
    public CategoryJpaEntity update(CategoryJpaEntity categoryDto) {
        entityManager.flush();
        entityManager.merge(categoryDto);
        return categoryDto;
    }

    @Override
    public void delete(Integer idCategory) {
        CategoryJpaEntity categoryJpaEntity = entityManager.find(CategoryJpaEntity.class, idCategory);
        if (categoryJpaEntity != null) {
            entityManager.remove(categoryJpaEntity);
        }
    }

    @Override
    public CategoryJpaEntity insert(CategoryJpaEntity categoryDto) {
        entityManager.persist(categoryDto);
        return categoryDto;
    }

    @Override
    public CategoryJpaEntity getById(Integer idCategory) {
        return entityManager.find(CategoryJpaEntity.class, idCategory);
    }
}
