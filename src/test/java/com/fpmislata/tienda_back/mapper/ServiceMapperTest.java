package com.fpmislata.tienda_back.mapper;

import com.fpmislata.tienda_back.domain.service.dto.ServiceDto;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.ServiceJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ServiceMapperTest {
    private final String id_pruebas= "a1";
    private final String name_pruebas= "Service Pruebas";
    private final String description_pruebas= "Description Pruebas";
    private final Double price_pruebas= 99.99;
    private final String pictureUrl_pruebas= "http://example.com/picture.jpg";

    private ServiceJpaEntity createServiceJpaEntityTest() {
        return new ServiceJpaEntity(
                id_pruebas,
                name_pruebas,
                description_pruebas,
                price_pruebas,
                pictureUrl_pruebas
        );
    }

    private ServiceDto createServiceDtoTest() {
        return new ServiceDto(
                id_pruebas,
                name_pruebas,
                description_pruebas,
                price_pruebas,
                pictureUrl_pruebas
        );
    }

    @Nested class FromJpaEntityToDto {
        @Test
        @DisplayName("Mapeo Entity->Dto")
        void testFromJpaEntityToDtoShouldMapAllFields() {

            ServiceJpaEntity jpaEntity = createServiceJpaEntityTest();


            ServiceDto resultDto = ServiceMapper.fromServiceJpaEntityToServiceEntity(jpaEntity);

            assertThat(resultDto).isNotNull();
            assertThat(resultDto.id_service()).isEqualTo(jpaEntity.getId_service());
            assertThat(resultDto.name()).isEqualTo(jpaEntity.getName());
            assertThat(resultDto.description()).isEqualTo(jpaEntity.getDescription());
            assertThat(resultDto.price()).isEqualTo(jpaEntity.getPrice());
            assertThat(resultDto.pictureUrl()).isEqualTo(jpaEntity.getPictureUrl());
        }

        @Test
        @DisplayName("Mapeo Entity nulo->Dto nulo")
        void testFromJpaEntityToDtoShouldReturnNullWhenInputIsNull() {
            ServiceDto resultDto = ServiceMapper.fromServiceJpaEntityToServiceEntity(null);
            assertThat(resultDto).isNull();
        }
    }

    @Nested class FromDtoToJpaEntity {
        @Test
        @DisplayName("Mapeo Dto->Entity")
        void testFromDtoToJpaEntityShouldMapAllFields() {
            ServiceDto serviceDto = createServiceDtoTest();

            ServiceJpaEntity resultEntity = ServiceMapper.fromServiceEntityToServiceJpaEntity(serviceDto);

            assertThat(resultEntity).isNotNull();
            assertThat(resultEntity.getId_service()).isEqualTo(serviceDto.id_service());
            assertThat(resultEntity.getName()).isEqualTo(serviceDto.name());
            assertThat(resultEntity.getDescription()).isEqualTo(serviceDto.description());
            assertThat(resultEntity.getPrice()).isEqualTo(serviceDto.price());
            assertThat(resultEntity.getPictureUrl()).isEqualTo(serviceDto.pictureUrl());
        }

        @Test
        @DisplayName("Mapeo Dto nulo->Entity nulo")
        void testFromDtoToJpaEntityShouldReturnNullWhenInputIsNull() {
            ServiceJpaEntity resultEntity = ServiceMapper.fromServiceEntityToServiceJpaEntity(null);
            assertThat(resultEntity).isNull();
        }
    }

    @Nested class FromDtoToResponse {
        @Test
        @DisplayName("Mapeo Dto->Response")
        void testFromDtoToResponseShouldMapAllFields() {
            ServiceDto serviceDto = createServiceDtoTest();

            var resultResponse = ServiceMapper.fromServiceDtoToServiceDetailResponse(serviceDto);

            assertThat(resultResponse).isNotNull();
            assertThat(resultResponse.id_service()).isEqualTo(serviceDto.id_service());
            assertThat(resultResponse.name()).isEqualTo(serviceDto.name());
            assertThat(resultResponse.description()).isEqualTo(serviceDto.description());
            assertThat(resultResponse.price()).isEqualTo(serviceDto.price());
            assertThat(resultResponse.pictureUrl()).isEqualTo(serviceDto.pictureUrl());
        }
        @Test
        @DisplayName("Mapeo Dto nulo->Response nulo")
        void testFromDtoToResponseShouldReturnNullWhenInputIsNull() {
            var resultResponse = ServiceMapper.fromServiceDtoToServiceDetailResponse(null);
            assertThat(resultResponse).isNull();
        }
    }

}