package com.fpmislata.tienda_back.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpmislata.tienda_back.controller.webModel.request.CategoryInsertRequest;
import com.fpmislata.tienda_back.controller.webModel.response.CategoryDetailResponse;
import com.fpmislata.tienda_back.domain.service.CategoryService;
import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    private CategoryDto categoryDto;
    private CategoryInsertRequest categoryInsertRequest;

    @BeforeEach
    void setUp() {
        categoryDto = new CategoryDto(1, "Reparaciones");
        categoryInsertRequest = new CategoryInsertRequest(null, "Nueva Categoría");
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {
        @Test
        @DisplayName("Debería devolver 200 y una lista de categorías")
        void shouldReturnOkAndListOfCategories() throws Exception {
            when(categoryService.findAll()).thenReturn(List.of(categoryDto));

            mockMvc.perform(get("/api/categories"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].idCategory").value(1))
                    .andExpect(jsonPath("$[0].name").value("Reparaciones"));
        }
    }

    @Nested
    @DisplayName("Tests para el método getById")
    class GetByIdTests {
        @Test
        @DisplayName("Debería devolver 200 cuando la categoría existe")
        void shouldReturnOkWhenCategoryExists() throws Exception {
            when(categoryService.getById(1)).thenReturn(categoryDto);

            mockMvc.perform(get("/api/categories/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.idCategory").value(1))
                    .andExpect(jsonPath("$.name").value("Reparaciones"));
        }
    }

    @Nested
    @DisplayName("Tests para el método create")
    class CreateTests {
        @Test
        @DisplayName("Debería devolver 201 cuando la petición es válida")
        void shouldReturnCreatedWhenValid() throws Exception {
            CategoryDto createdDto = new CategoryDto(2, "Nueva Categoría");
            when(categoryService.create(any(CategoryDto.class))).thenReturn(createdDto);

            mockMvc.perform(post("/api/categories")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(categoryInsertRequest)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.idCategory").value(2))
                    .andExpect(jsonPath("$.name").value("Nueva Categoría"));
        }
    }

    @Nested
    @DisplayName("Tests para el método update")
    class UpdateTests {
        @Test
        @DisplayName("Debería devolver 200 cuando la petición es válida")
        void shouldReturnOkWhenValid() throws Exception {
            CategoryDto updatedDto = new CategoryDto(1, "Categoría Actualizada");
            when(categoryService.update(any(CategoryDto.class))).thenReturn(updatedDto);

            CategoryInsertRequest updateRequest = new CategoryInsertRequest(null, "Categoría Actualizada");

            mockMvc.perform(put("/api/categories/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateRequest)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.idCategory").value(1))
                    .andExpect(jsonPath("$.name").value("Categoría Actualizada"));
        }
    }

    @Nested
    @DisplayName("Tests para el método delete")
    class DeleteTests {
        @Test
        @DisplayName("Debería devolver 204 cuando el borrado es exitoso")
        void shouldReturnNoContent() throws Exception {
            doNothing().when(categoryService).delete(anyInt());

            mockMvc.perform(delete("/api/categories/1"))
                    .andExpect(status().isNoContent());
        }
    }
}
