package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.domain.repository.CategoryRepository;
import com.fpmislata.tienda_back.domain.repository.entity.CategoryEntity;
import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;
import com.fpmislata.tienda_back.exception.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Nested
    class TestsFindAllCategories {
        @Test
        @DisplayName("Test findAll should return list of categories when categories exist")
        void testFindAll_ShouldReturnListOfCategories_WhenCategoriesExist() {
            List<CategoryDto> expectedCategoriesDto = List.of(
                    new CategoryDto(1, "Cat1"),
                    new CategoryDto(2, "Cat2"));

            when(categoryRepository.findAll()).thenReturn(expectedCategoriesDto);

            List<CategoryDto> actualCategories = categoryService.findAll();

            assertAll(
                    () -> assertEquals(expectedCategoriesDto.get(0).idCategory(), actualCategories.get(0).idCategory()),
                    () -> assertEquals(expectedCategoriesDto.get(0).name(), actualCategories.get(0).name()),
                    () -> assertEquals(expectedCategoriesDto.get(1).idCategory(), actualCategories.get(1).idCategory()),
                    () -> assertEquals(expectedCategoriesDto.get(1).name(), actualCategories.get(1).name())

            );
            verify(categoryRepository, times(1)).findAll();
        }

        @Test
        @DisplayName("Test findAll should return empty list when no categories exist")
        void testFindAll_ShouldReturnEmptyList_WhenNoCategoriesExist() {
            when(categoryRepository.findAll()).thenReturn(List.of());
            List<CategoryDto> actualCategories = categoryService.findAll();
            assertTrue(actualCategories.isEmpty());
            verify(categoryRepository, times(1)).findAll();
        }
    }

    @Nested
    class TestsFindCategoryById {
        @Test
        @DisplayName("Test findCategoryById should return category when category exists")
        void testFindCategoryById_ShouldReturnCategory_WhenCategoryExists() {
            Integer categoryId = 1;
            CategoryDto categoryDto = new CategoryDto(1, "Cat1");
            when(categoryRepository.findCategoryById(categoryId)).thenReturn(Optional.of(categoryDto));
            Optional<CategoryDto> actualCategory = categoryService.findCategoryById(categoryId);
            assertAll(
                    () -> assertEquals(categoryDto.idCategory(), actualCategory.get().idCategory()),
                    () -> assertEquals(categoryDto.name(), actualCategory.get().name()));
            verify(categoryRepository, times(1)).findCategoryById(categoryId);
        }

        @Test
        @DisplayName("Test findCategoryById should throw ResourceNotFoundException when category does not exist")
        void testFindCategoryById_ShouldThrowResourceNotFoundException_WhenCategoryDoesNotExist() {
            Integer categoryId = 1;
            when(categoryRepository.findCategoryById(categoryId)).thenReturn(Optional.empty());
            assertThrows(ResourceNotFoundException.class, () -> categoryService.findCategoryById(categoryId));
            verify(categoryRepository, times(1)).findCategoryById(categoryId);
        }

    }

    @Nested
    class TestsCreateCategory {
        @Test
        @DisplayName("Test create should return created category")
        void testCreate_ShouldReturnCreatedCategory() {
            CategoryDto categoryDtoToCreate = new CategoryDto(null, "New Category");
            CategoryDto createdCategoryDto = new CategoryDto(1, "New Category");

            when(categoryRepository.create(categoryDtoToCreate)).thenReturn(createdCategoryDto);

            CategoryDto actualCreatedCategory = categoryService.create(categoryDtoToCreate);

            assertAll(
                    () -> assertEquals(createdCategoryDto.idCategory(), actualCreatedCategory.idCategory()),
                    () -> assertEquals(createdCategoryDto.name(), actualCreatedCategory.name()));
            verify(categoryRepository, times(1)).create(categoryDtoToCreate);
        }

        @Test
        @DisplayName("Test create should throw IllegalArgumentException when category already exists")
        void testCreate_ShouldThrowIllegalArgumentException_WhenCategoryAlreadyExists() {
            CategoryDto categoryToCreate = new CategoryDto(1, "Existing Category");

            try {
                categoryService.create(categoryToCreate);
            } catch (Exception e) {
                assertEquals("Category already exists", e.getMessage());
            }
        }
    }

    @Nested
    class TestsDeleteCategory {
        @Test
        @DisplayName("Test delete should delete category when category exists")
        void testDelete_ShouldDeleteCategory_WhenCategoryExists() {
            Integer categoryId = 1;
            CategoryDto existingCategoryDto = new CategoryDto(1, "Cat1");

            when(categoryRepository.findCategoryById(categoryId)).thenReturn(Optional.of(existingCategoryDto));
            categoryService.delete(categoryId);
            verify(categoryRepository, times(1)).findCategoryById(categoryId);
            verify(categoryRepository, times(1)).delete(categoryId);
        }

        @Test
        @DisplayName("Test delete should throw ResourceNotFoundException when category does not exist")
        void testDelete_ShouldThrowResourceNotFoundException_WhenCategoryDoesNotExist() {
            Integer categoryId = 1;
            when(categoryRepository.findCategoryById(categoryId)).thenReturn(Optional.empty());
            assertThrows(ResourceNotFoundException.class, () -> categoryService.delete(categoryId));
            verify(categoryRepository, times(1)).findCategoryById(categoryId);
            verify(categoryRepository, times(0)).delete(categoryId);
        }
    }

    @Nested
    class TestsUpdateCategory {
        @Test
        @DisplayName("Test update should return updated category when category exists")
        void testUpdate_ShouldReturnUpdatedCategory_WhenCategoryExists() {
            CategoryDto categoryDtoToUpdate = new CategoryDto(1, "Updated Category");
            CategoryDto updatedCategoryDto = new CategoryDto(1, "Updated Category");
            when(categoryRepository.findCategoryById(categoryDtoToUpdate.idCategory()))
                    .thenReturn(Optional.of(categoryDtoToUpdate));
            when(categoryRepository.update(categoryDtoToUpdate)).thenReturn(updatedCategoryDto);
            CategoryDto actualUpdatedCategory = categoryService.update(categoryDtoToUpdate);
            assertAll(
                    () -> assertEquals(updatedCategoryDto.idCategory(), actualUpdatedCategory.idCategory()),
                    () -> assertEquals(updatedCategoryDto.name(), actualUpdatedCategory.name()));
            verify(categoryRepository, times(1)).findCategoryById(categoryDtoToUpdate.idCategory());
            verify(categoryRepository, times(1)).update(categoryDtoToUpdate);
        }

        @Test
        @DisplayName("Test update should throw ResourceNotFoundException when category does not exist")
        void testUpdate_ShouldThrowResourceNotFoundException_WhenCategoryDoesNotExist() {
            CategoryDto categoryDtoToUpdate = new CategoryDto(1, "Updated Category");
            when(categoryRepository.findCategoryById(categoryDtoToUpdate.idCategory())).thenReturn(Optional.empty());
            assertThrows(ResourceNotFoundException.class, () -> categoryService.update(categoryDtoToUpdate));
            verify(categoryRepository, times(1)).findCategoryById(categoryDtoToUpdate.idCategory());
            verify(categoryRepository, times(0)).update(categoryDtoToUpdate);
        }
    }

    @Nested
    class TestsGetById {
        @Test
        @DisplayName("Test getById should return category when category exists")
        void testGetById_ShouldReturnCategory_WhenCategoryExists() {
            Integer categoryId = 1;
            CategoryDto categoryDto = new CategoryDto(1, "Cat1");
            when(categoryRepository.findCategoryById(categoryId)).thenReturn(Optional.of(categoryDto));
            CategoryDto actualCategory = categoryService.getById(categoryId);
            assertAll(
                    () -> assertEquals(categoryDto.idCategory(), actualCategory.idCategory()),
                    () -> assertEquals(categoryDto.name(), actualCategory.name()));
            verify(categoryRepository, times(1)).findCategoryById(categoryId);
        }

        @Test
        @DisplayName("Test getById should throw ResourceNotFoundException when category does not exist")
        void testGetById_ShouldThrowResourceNotFoundException_WhenCategoryDoesNotExist() {
            Integer categoryId = 1;
            when(categoryRepository.findCategoryById(categoryId)).thenReturn(Optional.empty());
            assertThrows(ResourceNotFoundException.class, () -> categoryService.getById(categoryId));
            verify(categoryRepository, times(1)).findCategoryById(categoryId);
        }
    }

}