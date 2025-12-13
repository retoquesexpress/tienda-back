package com.fpmislata.tienda_back.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    @Test
    @DisplayName("Test Service Constructor and Getters")
    void testServiceConstructorAndGetters() {
        Service service = new Service("1", "Test Service", "This is a test service", 99.99, "http://example.com/image.jpg");
        assertEquals("1", service.getId());
        assertEquals("Test Service", service.getName());
        assertEquals("This is a test service", service.getDescription());
        assertEquals(99.99, service.getPrice());
        assertEquals("http://example.com/image.jpg", service.getPictureUrl());
    }

    @Test
    @DisplayName("Test Service Setters")
    void testServiceSetters() {
        Service service = new Service("1", "Test Service", "This is a test service", 99.99, "http://example.com/image.jpg");
        service.setId("2");
        service.setName("Updated Service");
        service.setDescription("This is an updated test service");
        service.setPrice(149.99);
        service.setPictureUrl("http://example.com/updated_image.jpg");
        assertEquals("2", service.getId());
        assertEquals("Updated Service", service.getName());
        assertEquals("This is an updated test service", service.getDescription());
        assertEquals(149.99, service.getPrice());
        assertEquals("http://example.com/updated_image.jpg", service.getPictureUrl());
    }
}