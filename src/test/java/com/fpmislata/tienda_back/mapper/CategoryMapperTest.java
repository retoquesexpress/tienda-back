package com.fpmislata.tienda_back.mapper;

import com.fpmislata.tienda_back.controller.webModel.request.CategoryInsertRequest;
import com.fpmislata.tienda_back.controller.webModel.request.CategoryUpdateRequest;
import com.fpmislata.tienda_back.controller.webModel.response.CategoryDetailResponse;
import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CategoryMapperTest {
    private final Integer idCategory= 1;
    private final String name= "User";

    private CategoryJpaEntity createCategoryJpaEntityTest() {
        return new CategoryJpaEntity(
                idCategory,
                name
        );
    }

    private CategoryDto createCategoryDtoTest() {
        return new CategoryDto(
                idCategory,
                name
        );
    }

    private CategoryInsertRequest createCategoryInsertRequestTest() {
        return new CategoryInsertRequest(
                idCategory,
                name
        );
    }

    private CategoryUpdateRequest createCategoryUpdateRequestTest() {
        return new CategoryUpdateRequest(
                idCategory,
                name
        );
    }



    @Nested
    class fromCategoryJpaEntityToCategoryDto {
        @Test
        @DisplayName("Mapeo JpaEntity->Dto")
        void testFromJpaEntityToDtoShouldMapAllFields() {

            CategoryJpaEntity jpaEntity = createCategoryJpaEntityTest();


            CategoryDto resultDto = CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryDto(jpaEntity);

            assertThat(resultDto).isNotNull();
            assertThat(resultDto.idCategory()).isEqualTo(jpaEntity.getIdCategory());
            assertThat(resultDto.name()).isEqualTo(jpaEntity.getName());
        }


        @Test
        @DisplayName("Mapeo JpaEntity nulo->Dto nulo")
        void testFromJpaEntityToDtoShouldReturnNullWhenInputIsNull() {
            CategoryDto resultDto = CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryDto(null);
            assertThat(resultDto).isNull();
        }
    }

    @Nested
    class fromCategoryDtoToCategoryJpaEntity {
        @Test
        @DisplayName("Mapeo Dto->JpaEntity")
        void testFromDtoToJpaEntityShouldMapAllFields() {

            CategoryDto categoryDto = createCategoryDtoTest();


            CategoryJpaEntity resultJpaEntity = CategoryMapper.getInstance().fromCategoryDtoToCategoryJpaEntity(categoryDto);

            assertThat(resultJpaEntity).isNotNull();
            assertThat(resultJpaEntity.getIdCategory()).isEqualTo(categoryDto.idCategory());
            assertThat(resultJpaEntity.getName()).isEqualTo(categoryDto.name());
        }


        @Test
        @DisplayName("Mapeo Dto nulo->JpaEntity nulo")
        void testFromDtoToJpaEntityShouldReturnNullWhenInputIsNull() {
            CategoryJpaEntity result = CategoryMapper.getInstance().fromCategoryDtoToCategoryJpaEntity(null);
            assertThat(result).isNull();
        }
    }

    @Nested
    class fromCategoryDtoToCategoryDetailResponse {
        @Test
        @DisplayName("Mapeo Dto->DetailResponse")
        void testFromDtoToDetailResponseShouldMapAllFields() {

            CategoryDto categoryDto = createCategoryDtoTest();


            CategoryDetailResponse result = CategoryMapper.getInstance().fromCategoryDtoToCategoryDetailResponse(categoryDto);

            assertThat(result).isNotNull();
            assertThat(result.idCategory()).isEqualTo(categoryDto.idCategory());
            assertThat(result.name()).isEqualTo(categoryDto.name());
        }


        @Test
        @DisplayName("Mapeo Dto nulo->DetailResponse nulo")
        void testFromDtoToDetailResponseShouldReturnNullWhenInputIsNull() {
            CategoryDetailResponse result = CategoryMapper.getInstance().fromCategoryDtoToCategoryDetailResponse(null);
            assertThat(result).isNull();
        }
    }

    @Nested
    class fromCategoryInsertRequestToCategoryDto {
        @Test
        @DisplayName("Mapeo InsertRequest->Dto")
        void testFromInsertRequestToDtoShouldMapAllFields() {

            CategoryInsertRequest categoryInsertRequest = createCategoryInsertRequestTest();


            CategoryDto result = CategoryMapper.getInstance().fromCategoryInsertRequestToCategoryDto(categoryInsertRequest);

            assertThat(result).isNotNull();
            assertThat(result.idCategory()).isEqualTo(categoryInsertRequest.idCategory());
            assertThat(result.name()).isEqualTo(categoryInsertRequest.name());
        }


        @Test
        @DisplayName("Mapeo InsertRequest nulo->Dto nulo")
        void testFromInsertRequestToDtoShouldReturnNullWhenInputIsNull() {
            CategoryDto result = CategoryMapper.getInstance().fromCategoryInsertRequestToCategoryDto(null);
            assertThat(result).isNull();
        }
    }

    @Nested
    class fromCategoryUpdateRequestToCategoryDto {
        @Test
        @DisplayName("Mapeo UpdateRequest->Dto")
        void testFromCategoryUpdateRequestToCategoryDtoShouldMapAllFields() {

            CategoryUpdateRequest categoryUpdateRequest = createCategoryUpdateRequestTest();


            CategoryDto result = CategoryMapper.getInstance().fromCategoryUpdateRequestToCategoryDto(categoryUpdateRequest);

            assertThat(result).isNotNull();
            assertThat(result.idCategory()).isEqualTo(categoryUpdateRequest.idCategory());
            assertThat(result.name()).isEqualTo(categoryUpdateRequest.name());
        }


        @Test
        @DisplayName("Mapeo Dto UpdateRequest->Dto nulo")
        void testFromCategoryUpdateRequestToCategoryDtoShouldReturnNullWhenInputIsNull() {
            CategoryDto result = CategoryMapper.getInstance().fromCategoryUpdateRequestToCategoryDto(null);
            assertThat(result).isNull();
        }
    }



}