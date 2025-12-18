package com.fpmislata.tienda_back.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    @Test
    @DisplayName("Test Category Constructor and Getters")
    void testCategoryConstructorAndGetters() {
        Category category = new Category(1, "Electronics");
        assertEquals(1, category.getId());
        assertEquals("Electronics", category.getName());
    }
    @Test
    @DisplayName("Test Category Setters")
    void testCategorySetters() {
        Category category = new Category(1, "Electronics");
        category.setId(2);
        category.setName("Books");
        assertEquals(2, category.getId());
        assertEquals("Books", category.getName());
    }

}