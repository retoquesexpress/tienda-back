package com.fpmislata.tienda_back.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpmislata.tienda_back.controller.webModel.request.CategoryInsertRequest;
import com.fpmislata.tienda_back.controller.webModel.request.CategoryUpdateRequest;
import com.fpmislata.tienda_back.controller.webModel.request.ServiceInsertRequest;
import com.fpmislata.tienda_back.controller.webModel.request.ServiceUpdateRequest;
import com.fpmislata.tienda_back.controller.webModel.response.ServiceDetailResponse;
import com.fpmislata.tienda_back.domain.service.ServiceService;
import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;
import com.fpmislata.tienda_back.domain.service.dto.ServiceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ServiceController.class)
public class ServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceService serviceService;

    @Autowired
    private ObjectMapper objectMapper;

    private ServiceEntity serviceEntity;
    private ServiceInsertRequest serviceInsertRequest;
    private ServiceUpdateRequest serviceUpdateRequest;
    private CategoryDto categoryDto;

    @BeforeEach
    void setUp() {
        categoryDto = new CategoryDto(1, "Reparaciones");
        serviceEntity = new ServiceEntity(1, "Reparación de móviles", "Servicio de reparación", 
            50.0, "http://example.com/pic.jpg", categoryDto);
        
        CategoryInsertRequest categoryInsertRequest = new CategoryInsertRequest(1, "Reparaciones");
        serviceInsertRequest = new ServiceInsertRequest("Nuevo Servicio", "Descripción", 
            100.0, "http://example.com/new.jpg", categoryInsertRequest);
        
        CategoryUpdateRequest categoryUpdateRequest = new CategoryUpdateRequest(1, "Reparaciones");
        serviceUpdateRequest = new ServiceUpdateRequest(1, "Servicio Actualizado", "Descripción actualizada", 
            150.0, "http://example.com/updated.jpg", categoryUpdateRequest);
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {
        @Test
        @DisplayName("Debería devolver 200 y una lista de servicios")
        void shouldReturnOkAndListOfServices() throws Exception {
            when(serviceService.findAll()).thenReturn(List.of(serviceEntity));

            mockMvc.perform(get("/api/services"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].idService").value(1))
                    .andExpect(jsonPath("$[0].name").value("Reparación de móviles"));
        }
    }

    @Nested
    @DisplayName("Tests para el método findByCategory")
    class FindByCategoryTests {
        @Test
        @DisplayName("Debería devolver 200 y una lista de servicios por categoría")
        void shouldReturnOkAndListOfServicesByCategory() throws Exception {
            when(serviceService.findByCategory(1)).thenReturn(List.of(serviceEntity));

            mockMvc.perform(get("/api/services/category/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].idService").value(1))
                    .andExpect(jsonPath("$[0].name").value("Reparación de móviles"));
        }
    }

    @Nested
    @DisplayName("Tests para el método getById")
    class GetByIdTests {
        @Test
        @DisplayName("Debería devolver 200 cuando el servicio existe")
        void shouldReturnOkWhenServiceExists() throws Exception {
            when(serviceService.getById(1)).thenReturn(serviceEntity);

            mockMvc.perform(get("/api/services/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.idService").value(1))
                    .andExpect(jsonPath("$.name").value("Reparación de móviles"));
        }
    }

    @Nested
    @DisplayName("Tests para el método create")
    class CreateTests {
        @Test
        @DisplayName("Debería devolver 201 cuando la petición es válida")
        void shouldReturnCreatedWhenValid() throws Exception {
            CategoryDto newCategory = new CategoryDto(1, "Reparaciones");
            ServiceEntity createdService = new ServiceEntity(2, "Nuevo Servicio", "Descripción", 
                100.0, "http://example.com/new.jpg", newCategory);
            when(serviceService.create(any(ServiceEntity.class))).thenReturn(createdService);

            mockMvc.perform(post("/api/services")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(serviceInsertRequest)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.idService").value(2))
                    .andExpect(jsonPath("$.name").value("Nuevo Servicio"));
        }
    }

    @Nested
    @DisplayName("Tests para el método update")
    class UpdateTests {
        @Test
        @DisplayName("Debería devolver 200 cuando la petición es válida")
        void shouldReturnOkWhenValid() throws Exception {
            CategoryDto updatedCategory = new CategoryDto(1, "Reparaciones");
            ServiceEntity updatedService = new ServiceEntity(1, "Servicio Actualizado", 
                "Descripción actualizada", 150.0, "http://example.com/updated.jpg", updatedCategory);
            when(serviceService.update(any(ServiceEntity.class))).thenReturn(updatedService);

            mockMvc.perform(put("/api/services/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(serviceUpdateRequest)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.idService").value(1))
                    .andExpect(jsonPath("$.name").value("Servicio Actualizado"));
    }
    }

    @Nested
    @DisplayName("Tests para el método delete")
    class DeleteTests {
        @Test
        @DisplayName("Debería devolver 204 cuando el borrado es exitoso")
        void shouldReturnNoContent() throws Exception {
            doNothing().when(serviceService).deleteById(anyInt());

            mockMvc.perform(delete("/api/services/1"))
                    .andExpect(status().isNoContent());
        }
    }
}
