package com.fpmislata.tienda_back.domain.service.dto;

import com.fpmislata.tienda_back.domain.repository.entity.CategoryEntity;
import com.fpmislata.tienda_back.domain.repository.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDtoTest {
    @Test
    @DisplayName("Test CategoryDto Creation")
    void testCategoryDtoCreation() {
        Integer id_category = 1;
        String name = "Category Test";


        CategoryEntity categoryEntity = new CategoryEntity(id_category, name);
        assertNotNull(categoryEntity);
        assertEquals(id_category, categoryEntity.idCategory());
        assertEquals(name, categoryEntity.name());
    }

    @Test
    @DisplayName("Test CategoryDto NotNulls required fields")
    void testCategoryDtoNotNullsRequiredfields() {
        Integer id_category = 1;
        String categoryName = "category";

        CategoryEntity categoryEntity = new CategoryEntity(id_category, categoryName);
        assertNotNull(categoryEntity.idCategory());
    }
}