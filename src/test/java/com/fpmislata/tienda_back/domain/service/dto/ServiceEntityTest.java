package com.fpmislata.tienda_back.domain.service.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceEntityTest {

    @Nested
    @DisplayName("Tests para la creación de ServiceEntity")
    class ServiceEntityCreationTests {
        @Test
        @DisplayName("Debería crear ServiceEntity correctamente con datos válidos")
        void shouldCreateServiceEntityWithValidData() {
            CategoryDto category = new CategoryDto(1, "Reparaciones");
            
            ServiceEntity service = new ServiceEntity(1, "Reparación de móviles", 
                "Servicio de reparación completo", 50.0, "http://example.com/pic.jpg", category);

            assertNotNull(service);
            assertEquals(1, service.idService());
            assertEquals("Reparación de móviles", service.name());
            assertEquals("Servicio de reparación completo", service.description());
            assertEquals(50.0, service.price());
            assertEquals("http://example.com/pic.jpg", service.pictureUrl());
            assertEquals(category, service.category());
        }

        @Test
        @DisplayName("Debería lanzar NullPointerException cuando price es NaN")
        void shouldThrowExceptionWhenPriceIsNaN() {
            CategoryDto category = new CategoryDto(1, "Reparaciones");

            assertThrows(NullPointerException.class, () -> {
                new ServiceEntity(1, "Servicio", "Descripción", Double.NaN, 
                    "http://example.com/pic.jpg", category);
            });
        }

        @Test
        @DisplayName("Debería lanzar NullPointerException cuando category id es null")
        void shouldThrowExceptionWhenCategoryIdIsNull() {
            CategoryDto category = new CategoryDto(null, "Reparaciones");

            assertThrows(NullPointerException.class, () -> {
                new ServiceEntity(1, "Servicio", "Descripción", 50.0, 
                    "http://example.com/pic.jpg", category);
            });
        }

        @Test
        @DisplayName("Debería permitir null en idService")
        void shouldAllowNullIdService() {
            CategoryDto category = new CategoryDto(1, "Reparaciones");
            
            ServiceEntity service = new ServiceEntity(null, "Nuevo Servicio", 
                "Descripción", 100.0, "http://example.com/pic.jpg", category);

            assertNotNull(service);
            assertNull(service.idService());
        }

        @Test
        @DisplayName("Debería permitir null en description y pictureUrl")
        void shouldAllowNullInOptionalFields() {
            CategoryDto category = new CategoryDto(1, "Reparaciones");
            
            ServiceEntity service = new ServiceEntity(1, "Servicio", null, 50.0, null, category);

            assertNotNull(service);
            assertNull(service.description());
            assertNull(service.pictureUrl());
        }
    }

    @Nested
    @DisplayName("Tests para validación de precios")
    class PriceValidationTests {
        @Test
        @DisplayName("Debería aceptar precio cero")
        void shouldAcceptZeroPrice() {
            CategoryDto category = new CategoryDto(1, "Reparaciones");
            
            ServiceEntity service = new ServiceEntity(1, "Servicio Gratis", 
                "Descripción", 0.0, "http://example.com/pic.jpg", category);

            assertNotNull(service);
            assertEquals(0.0, service.price());
        }

        @Test
        @DisplayName("Debería aceptar precio negativo")
        void shouldAcceptNegativePrice() {
            CategoryDto category = new CategoryDto(1, "Reparaciones");
            
            ServiceEntity service = new ServiceEntity(1, "Servicio", 
                "Descripción", -10.0, "http://example.com/pic.jpg", category);

            assertNotNull(service);
            assertEquals(-10.0, service.price());
        }

        @Test
        @DisplayName("Debería aceptar precio decimal")
        void shouldAcceptDecimalPrice() {
            CategoryDto category = new CategoryDto(1, "Reparaciones");
            
            ServiceEntity service = new ServiceEntity(1, "Servicio", 
                "Descripción", 49.99, "http://example.com/pic.jpg", category);

            assertNotNull(service);
            assertEquals(49.99, service.price());
        }
    }
}
