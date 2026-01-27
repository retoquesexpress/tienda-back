package com.fpmislata.tienda_back.persistence.dao.jpa.impl;

import com.fpmislata.tienda_back.persistence.TestConfig;
import com.fpmislata.tienda_back.persistence.dao.jpa.CategoryJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class CategoryJpaDaoImplTest {

    @Autowired
    private CategoryJpaDao categoryJpaDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DisplayName("Test findAll categories")
    void findAll() {
        List<CategoryJpaEntity> categories = categoryJpaDao.findAll();
        assertNotNull(categories);
        assertFalse(categories.isEmpty());
    }

    @Test
    @DisplayName("Test find category by id")
    void findCategoryById() {
        CategoryJpaEntity category = entityManager.find(CategoryJpaEntity.class, 1);
        Optional<CategoryJpaEntity> actual = categoryJpaDao.findCategoryById(category.getIdCategory());
        assertAll(
                () -> assertTrue(actual.isPresent()),
                () -> assertEquals(category.getIdCategory(), actual.get().getIdCategory()),
                () -> assertEquals(category.getName(), actual.get().getName())
        );
    }

    @Test
    @DisplayName("Test update category")
    void update() {
        CategoryJpaEntity category = entityManager.find(CategoryJpaEntity.class, 1);
        category.setName("Updated Category");
        CategoryJpaEntity updated = categoryJpaDao.update(category);
        assertAll(
                () -> assertEquals(category.getIdCategory(), updated.getIdCategory()),
                () -> assertEquals("Updated Category", updated.getName())
        );
    }

    @Test
    @DisplayName("Test delete category")
    void delete() {
        CategoryJpaEntity category = entityManager.find(CategoryJpaEntity.class, 1);
        categoryJpaDao.delete(category.getIdCategory());
        Optional<CategoryJpaEntity> actual = categoryJpaDao.findCategoryById(category.getIdCategory());
        assertFalse(actual.isPresent());
    }

    @Test
    @DisplayName("Test insert category")
    void insert() {
        CategoryJpaEntity category = new CategoryJpaEntity(null, "New Category");
        CategoryJpaEntity inserted = categoryJpaDao.insert(category);
        assertAll(
                () -> assertNotNull(inserted),
                () -> assertEquals(category.getName(), inserted.getName())
        );
    }

    @Test
    @DisplayName("Test get category by id")
    void getById() {    
        CategoryJpaEntity category = entityManager.find(CategoryJpaEntity.class, 1);
        CategoryJpaEntity found = categoryJpaDao.getById(category.getIdCategory());
        assertAll(
                () -> assertNotNull(found),
                () -> assertEquals(category.getIdCategory(), found.getIdCategory()),
                () -> assertEquals(category.getName(), found.getName())
        );
    }
}