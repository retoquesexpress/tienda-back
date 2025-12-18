package com.fpmislata.tienda_back.domain.service.dto;

import com.fpmislata.tienda_back.domain.model.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceDtoTest {

    @Test
    @DisplayName("Test ServiceDto Creation")
    void testServiceDtoCreation() {
        Integer idService = 1;
        String name = "Test Service";
        String description = "This is a test service.";
        double price = 99.99;
        String pictureUrl = "http://example.com/image.jpg";
        CategoryDto categoryDto = new CategoryDto(1, "Test Category");
        ServiceEntity serviceDto = new ServiceEntity(1, name, description, price, pictureUrl, categoryDto);
        assertNotNull(serviceDto);
        assertEquals(idService, serviceDto.idService());
        assertEquals(name, serviceDto.name());
        assertEquals(description, serviceDto.description());
        assertEquals(price, serviceDto.price());
        assertEquals(pictureUrl, serviceDto.pictureUrl());
        assertEquals(categoryDto, serviceDto.category());
    }

    @Test
    @DisplayName("Test ServiceDto NotNulls required fields")
    void testServiceDtoNotNullsRequiredfields() {
        Integer idService = 1;
        double price = 99.99;
        CategoryDto categoryDto = new CategoryDto(1, "Test Category");
        ServiceEntity serviceDto = new ServiceEntity(1, null, null, price, null, categoryDto);
        assertNotNull(serviceDto.idService());
        assertNotNull(serviceDto.price());
        assertNotNull(serviceDto.category());
    }

    @Test
    @DisplayName("Test ServiceDto Null Fields")
    void testServiceDtoNullFields() {
        Integer idService = 1;
        double price = 99.99;
        CategoryDto categoryDto = new CategoryDto(1, "Test Category");
        ServiceEntity serviceDto = new ServiceEntity(1, null, null, price, null, categoryDto);
        assertNull(serviceDto.name());
        assertNull(serviceDto.description());
        assertNull(serviceDto.pictureUrl());
    }
}