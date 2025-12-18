package com.fpmislata.tienda_back.persistence.repository;

import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;
import com.fpmislata.tienda_back.persistence.dao.jpa.CategoryJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryRepositoryImplTest {
    @Mock
    private CategoryJpaDao categoryJpaDao;

    @InjectMocks
    private CategoryRepositoryImpl categoryRepositoryImpl;

    @Nested
    class FindAllCategories {
        @Test
        @DisplayName("Test findAllCategories should return list of categories when categories exist")
        void testFindAllCategories_ReturnsListOfCategories_WhenCategoriesExist() {
            //Arrange
            List<CategoryJpaEntity> expectedCategories = List.of(
                    new CategoryJpaEntity(1, "CATEGORY1"),
                    new CategoryJpaEntity(2, "CATEGORY2")
                );
            when(categoryJpaDao.findAll()).thenReturn(expectedCategories);
            //Act
            List<CategoryDto> actualCategories = categoryRepositoryImpl.findAll();
            //Assert
            assertAll(
                    () -> assertEquals(expectedCategories.get(0).getIdCategory(), actualCategories.get(0).idCategory()),
                    () -> assertEquals(expectedCategories.get(0).getName(), actualCategories.get(0).name()),

                    () -> assertEquals(expectedCategories.get(1).getIdCategory(), actualCategories.get(1).idCategory()),
                    () -> assertEquals(expectedCategories.get(1).getName(), actualCategories.get(1).name())
            );
            verify(categoryJpaDao).findAll();
        }

        @Test
        @DisplayName("Test findAllCategories should return empty list when no categories exist")
        void testFindAllCategories_ReturnsEmptyList_WhenNoCategoriesExist() {
            //Arrange
            when(categoryJpaDao.findAll()).thenReturn(List.of());
            //Act
            List<CategoryDto> actualCategories = categoryRepositoryImpl.findAll();
            //Assert
            assertTrue(actualCategories.isEmpty());
            verify(categoryJpaDao).findAll();
        }
    }

    @Nested
    class FindCategoryById {
        @Test
        @DisplayName("Test findCategoryById should return category when category exists")
        void testFindCategoryById_ReturnsCategory_WhenCategoryExists() {
            //Arrange
            CategoryJpaEntity expectedCategory = new CategoryJpaEntity(1, "CATEGORY1");
            when(categoryJpaDao.findCategoryById(expectedCategory.getIdCategory())).thenReturn(Optional.of(expectedCategory));
            //Act
            Optional<CategoryDto> actualCategory = categoryRepositoryImpl.findCategoryById(expectedCategory.getIdCategory());
            //Assert
            assertAll(
                    () -> assertEquals(expectedCategory.getIdCategory(), actualCategory.get().idCategory()),
                    () -> assertEquals(expectedCategory.getName(), actualCategory.get().name())
            );
            verify(categoryJpaDao).findCategoryById(expectedCategory.getIdCategory());
        }

        @Test
        @DisplayName("Test findCategoryById should return empty when category does not exist")
        void testFindCategoryById_ReturnsEmpty_WhenCategoryDoesNotExist() {
            //Arrange
            Integer categoryId = 111;
            when(categoryJpaDao.findCategoryById(categoryId)).thenReturn(Optional.empty());
            //Act
            Optional<CategoryDto> actualCategory = categoryRepositoryImpl.findCategoryById(categoryId);
            //Assert
            assertTrue(actualCategory.isEmpty());
            verify(categoryJpaDao).findCategoryById(categoryId);
        }
    }

    @Nested
    class TestsDeleteCategory {
        @Test
        @DisplayName("Test deleteCategory should call delete method of categoryJpaDao")
        void testDeleteCategory_CallsDeleteMethodOfCategoryJpaDao() {
            //Arrange
            Integer categoryId = 1;
            //Act
            categoryRepositoryImpl.delete(categoryId);
            //Assert
            verify(categoryJpaDao).delete(categoryId);
        }

    }

    @Nested
    class TestsCreateCategory {
        @Test
        @DisplayName("Test createCategory should return created category")
        void testCreateCategory_ReturnsCreatedCategory() {
            //Arrange
            CategoryDto categoryDtoToCreate = new CategoryDto(null, "NEW_CATEGORY");
            CategoryJpaEntity categoryJpaEntityToCreate = new CategoryJpaEntity(null, "NEW_CATEGORY");
            CategoryJpaEntity createdCategoryJpaEntity = new CategoryJpaEntity(1, "NEW_CATEGORY");

            when(categoryJpaDao.insert(categoryJpaEntityToCreate)).thenReturn(createdCategoryJpaEntity);
            //Act
            CategoryDto actualCreatedCategory = categoryRepositoryImpl.create(categoryDtoToCreate);
            //Assert
            assertAll(
                    () -> assertEquals(createdCategoryJpaEntity.getIdCategory(), actualCreatedCategory.idCategory()),
                    () -> assertEquals(createdCategoryJpaEntity.getName(), actualCreatedCategory.name())
            );
            verify(categoryJpaDao).insert(categoryJpaEntityToCreate);
        }
    }

    @Nested
    class TestsUpdateCategory {
        @Test
        @DisplayName("Test updateCategory should return updated category when category exists")
        void testUpdateCategory_ReturnsUpdatedCategory_WhenCategoryExists() {
            //Arrange
            CategoryDto categoryDtoToUpdate = new CategoryDto(1, "UPDATED_CATEGORY");
            CategoryJpaEntity existingCategoryJpaEntity = new CategoryJpaEntity(1, "OLD_CATEGORY");
            CategoryJpaEntity updatedCategoryJpaEntity = new CategoryJpaEntity(1, "UPDATED_CATEGORY");

            when(categoryJpaDao.findCategoryById(categoryDtoToUpdate.idCategory())).thenReturn(Optional.of(existingCategoryJpaEntity));
            when(categoryJpaDao.update(existingCategoryJpaEntity)).thenReturn(updatedCategoryJpaEntity);
            //Act
            CategoryDto actualUpdatedCategory = categoryRepositoryImpl.update(categoryDtoToUpdate);
            //Assert
            assertAll(
                    () ->  assertEquals(updatedCategoryJpaEntity.getIdCategory(), actualUpdatedCategory.idCategory()),
                    () ->  assertEquals(updatedCategoryJpaEntity.getName(), actualUpdatedCategory.name())
            );
            verify(categoryJpaDao).findCategoryById(categoryDtoToUpdate.idCategory());
            verify(categoryJpaDao).update(existingCategoryJpaEntity);
        }
    }

    @Nested
    class TestsGetCategoryById {
        @Test
        @DisplayName("Test getCategoryById should return category when category exists")
        void testGetCategoryById_ReturnsCategory_WhenCategoryExists() {
            //Arrange
            CategoryJpaEntity expectedCategory = new CategoryJpaEntity(1, "CATEGORY1");
            when(categoryJpaDao.findCategoryById(expectedCategory.getIdCategory())).thenReturn(Optional.of(expectedCategory));
            //Act
            CategoryDto actualCategory = categoryRepositoryImpl.getById(expectedCategory.getIdCategory());
            //Assert
            assertAll(
                    () -> assertEquals(expectedCategory.getIdCategory(), actualCategory.idCategory()),
                    () -> assertEquals(expectedCategory.getName(), actualCategory.name())
            );
            verify(categoryJpaDao).findCategoryById(expectedCategory.getIdCategory());
        }

        @Test
        @DisplayName("Test getCategoryById should return null when category does not exist")
        void testGetCategoryById_ReturnsNull_WhenCategoryDoesNotExist() {
            //Arrange
            Integer categoryId = 111;
            when(categoryJpaDao.findCategoryById(categoryId)).thenReturn(Optional.empty());
            //Act
            CategoryDto actualCategory = categoryRepositoryImpl.getById(categoryId);
            //Assert
            assertNull(actualCategory);
            verify(categoryJpaDao).findCategoryById(categoryId);
        }
    }

}