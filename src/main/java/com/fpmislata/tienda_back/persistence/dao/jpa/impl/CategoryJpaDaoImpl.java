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
        return entityManager.createQuery("SELECT c FROM CategoryJpaEntity c ORDER BY c.id_category", CategoryJpaEntity.class)
                .getResultList();
    }

    @Override
    public Optional<CategoryJpaEntity> findCategoryById(String id_category) {
        CategoryJpaEntity categoryJpaEntity = entityManager.find(CategoryJpaEntity.class, id_category);
        return Optional.ofNullable(categoryJpaEntity);    }

    @Override
    public CategoryJpaEntity update(CategoryJpaEntity categoryDto) {
        entityManager.flush();
        entityManager.merge(categoryDto);
        return categoryDto;    }

    @Override
    public void delete(String id_category) {
        CategoryJpaEntity categoryJpaEntity = entityManager.find(CategoryJpaEntity.class, id_category);
        entityManager.createQuery("DELETE FROM CategoryJpaEntity c WHERE category.id_category = :id_category")
                .setParameter("id_category", id_category)
                .executeUpdate();
        entityManager.remove(categoryJpaEntity);
    }

    @Override
    public CategoryJpaEntity insert(CategoryJpaEntity categoryDto) {
        entityManager.persist(categoryDto);
        return categoryDto;    }

    @Override
    public CategoryJpaEntity getById(String id_category) {
        return entityManager.find(CategoryJpaEntity.class, id_category);
    }
}
