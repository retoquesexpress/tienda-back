package com.fpmislata.tienda_back.mapper;

import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;
import com.fpmislata.tienda_back.domain.service.dto.ServiceEntity;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.ServiceJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ServiceMapperTest {
    private final Integer id_pruebas= 1;
    private final String name_pruebas= "Service Pruebas";
    private final String description_pruebas= "Description Pruebas";
    private final Double price_pruebas= 99.99;
    private final String pictureUrl_pruebas= "http://example.com/picture.jpg";
    private final CategoryJpaEntity categoryJpaEntity_pruebas = new CategoryJpaEntity(1, "Category Pruebas");
    private final CategoryDto categoryDto_pruebas = new CategoryDto(1, "Category Pruebas");

    private ServiceJpaEntity createServiceJpaEntityTest() {
        return new ServiceJpaEntity(
                id_pruebas,
                name_pruebas,
                description_pruebas,
                price_pruebas,
                pictureUrl_pruebas,
                categoryJpaEntity_pruebas

        );
    }

    private ServiceEntity createServiceDtoTest() {
        return new ServiceEntity(
                id_pruebas,
                name_pruebas,
                description_pruebas,
                price_pruebas,
                pictureUrl_pruebas,
                categoryDto_pruebas

        );
    }

    @Nested class FromJpaEntityToDto {
        @Test
        @DisplayName("Mapeo Entity->Dto")
        void testFromJpaEntityToDtoShouldMapAllFields() {

            ServiceJpaEntity jpaEntity = createServiceJpaEntityTest();


            ServiceEntity resultDto = ServiceMapper.getInstance().fromServiceJpaEntityToServiceEntity(jpaEntity);

            assertThat(resultDto).isNotNull();
            assertThat(resultDto.idService()).isEqualTo(jpaEntity.getIdService());
            assertThat(resultDto.name()).isEqualTo(jpaEntity.getName());
            assertThat(resultDto.description()).isEqualTo(jpaEntity.getDescription());
            assertThat(resultDto.price()).isEqualTo(jpaEntity.getPrice());
            assertThat(resultDto.pictureUrl()).isEqualTo(jpaEntity.getPictureUrl());
            assertThat(resultDto.category().idCategory()).isEqualTo(jpaEntity.getCategory().getIdCategory());
            assertThat(resultDto.category().name()).isEqualTo(jpaEntity.getCategory().getName());
        }


        @Test
        @DisplayName("Mapeo Entity nulo->Dto nulo")
        void testFromJpaEntityToDtoShouldReturnNullWhenInputIsNull() {
            ServiceEntity resultDto = ServiceMapper.getInstance().fromServiceJpaEntityToServiceEntity(null);
            assertThat(resultDto).isNull();
        }
    }

    @Nested class FromDtoToJpaEntity {
        @Test
        @DisplayName("Mapeo Dto->Entity")
        void testFromDtoToJpaEntityShouldMapAllFields() {
            ServiceEntity serviceDto = createServiceDtoTest();

            ServiceJpaEntity resultEntity = ServiceMapper.getInstance().fromServiceEntityToServiceJpaEntity(serviceDto);

            assertThat(resultEntity).isNotNull();
            assertThat(resultEntity.getIdService()).isEqualTo(serviceDto.idService());
            assertThat(resultEntity.getName()).isEqualTo(serviceDto.name());
            assertThat(resultEntity.getDescription()).isEqualTo(serviceDto.description());
            assertThat(resultEntity.getPrice()).isEqualTo(serviceDto.price());
            assertThat(resultEntity.getPictureUrl()).isEqualTo(serviceDto.pictureUrl());
            assertThat(resultEntity.getCategory().getIdCategory()).isEqualTo(serviceDto.category().idCategory());
            assertThat(resultEntity.getCategory().getName()).isEqualTo(serviceDto.category().name());
        }

        @Test
        @DisplayName("Mapeo Dto nulo->Entity nulo")
        void testFromDtoToJpaEntityShouldReturnNullWhenInputIsNull() {
            ServiceJpaEntity resultEntity = ServiceMapper.getInstance().fromServiceEntityToServiceJpaEntity(null);
            assertThat(resultEntity).isNull();
        }
    }

    @Nested class FromDtoToResponse {
        @Test
        @DisplayName("Mapeo Dto->Response")
        void testFromDtoToResponseShouldMapAllFields() {
            ServiceEntity serviceDto = createServiceDtoTest();

            var resultResponse = ServiceMapper.getInstance().fromServiceDtoToServiceDetailResponse(serviceDto);

            assertThat(resultResponse).isNotNull();
            assertThat(resultResponse.idService()).isEqualTo(serviceDto.idService());
            assertThat(resultResponse.name()).isEqualTo(serviceDto.name());
            assertThat(resultResponse.description()).isEqualTo(serviceDto.description());
            assertThat(resultResponse.price()).isEqualTo(serviceDto.price());
            assertThat(resultResponse.pictureUrl()).isEqualTo(serviceDto.pictureUrl());
            assertThat(resultResponse.category().idCategory()).isEqualTo(serviceDto.category().idCategory());
            assertThat(resultResponse.category().name()).isEqualTo(serviceDto.category().name());
        }
        @Test
        @DisplayName("Mapeo Dto nulo->Response nulo")
        void testFromDtoToResponseShouldReturnNullWhenInputIsNull() {
            var resultResponse = ServiceMapper.getInstance().fromServiceDtoToServiceDetailResponse(null);
            assertThat(resultResponse).isNull();
        }
    }

}