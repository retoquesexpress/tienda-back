package com.fpmislata.tienda_back.domain.service.dto;

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
        ServiceDto serviceDto = new ServiceDto(id_service, name, description, price, pictureUrl);
        assertNotNull(serviceDto);
        assertEquals(id_service, serviceDto.id_service());
        assertEquals(name, serviceDto.name());
        assertEquals(description, serviceDto.description());
        assertEquals(price, serviceDto.price());
        assertEquals(pictureUrl, serviceDto.pictureUrl());
    }

    @Test
    @DisplayName("Test ServiceDto NotNulls")
    void testServiceDtoNotNulls() {
        String id_service = "service123";
        double price = 99.99;
        ServiceDto serviceDto = new ServiceDto(id_service, null, null, price, null);
        assertNotNull(serviceDto.id_service());
        assertNotNull(serviceDto.price());
    }

    @Test
    @DisplayName("Test ServiceDto Null Fields")
    void testServiceDtoNullFields() {
        String id_service = null;
        double price = 0.0;
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new ServiceDto(id_service, null, null, price, null);
        });
        String expectedMessage = "id_service must not be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}