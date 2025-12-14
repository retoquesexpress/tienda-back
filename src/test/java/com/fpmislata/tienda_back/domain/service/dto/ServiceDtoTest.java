package com.fpmislata.tienda_back.domain.service.dto;

import com.fpmislata.tienda_back.domain.model.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceDtoTest {

    @Test
    @DisplayName("Test ServiceDto Creation")
    void testServiceDtoCreation() {
        String id_service = "service123";
        String name = "Test Service";
        String description = "This is a test service.";
        double price = 99.99;
        String pictureUrl = "http://example.com/image.jpg";
        CategoryDto categoryDto = new CategoryDto("u1", "Test Category");
        ServiceEntity serviceDto = new ServiceEntity(id_service, name, description, price, pictureUrl,categoryDto);
        assertNotNull(serviceDto);
        assertEquals(id_service, serviceDto.id_service());
        assertEquals(name, serviceDto.name());
        assertEquals(description, serviceDto.description());
        assertEquals(price, serviceDto.price());
        assertEquals(pictureUrl, serviceDto.pictureUrl());
        assertEquals(categoryDto, serviceDto.category());
    }

    @Test
    @DisplayName("Test ServiceDto NotNulls required fields")
    void testServiceDtoNotNullsRequiredfields() {
        String id_service = "service123";
        double price = 99.99;
        CategoryDto categoryDto = new CategoryDto("u1", "Test Category");
        ServiceEntity serviceDto = new ServiceEntity(id_service, null, null, price, null,categoryDto);
        assertNotNull(serviceDto.id_service());
        assertNotNull(serviceDto.price());
        assertNotNull(serviceDto.category());
    }

    @Test
    @DisplayName("Test ServiceDto Null Fields")
    void testServiceDtoNullFields() {
        String id_service = "service123";
        double price = 99.99;
        CategoryDto categoryDto = new CategoryDto("u1", "Test Category");
        ServiceEntity serviceDto = new ServiceEntity(id_service, null, null, price, null,categoryDto);
        assertNull(serviceDto.name());
        assertNull(serviceDto.description());
        assertNull(serviceDto.pictureUrl());
    }
}