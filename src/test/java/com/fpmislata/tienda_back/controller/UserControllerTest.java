package com.fpmislata.tienda_back.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpmislata.tienda_back.controller.webModel.request.UserInsertRequest;
import com.fpmislata.tienda_back.controller.webModel.response.UserDetailResponse;
import com.fpmislata.tienda_back.domain.service.UserService;
import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserDto userDto;
    private UserInsertRequest userInsertRequest;

    @BeforeEach
    void setUp() {
        userDto = new UserDto(1, "John Doe", "john@example.com", "johndoe", 
            "password123", "123456789", "123 Main St", null, "USER");
        userInsertRequest = new UserInsertRequest(null, "Jane Doe", "jane@example.com", 
            "janedoe", "987654321", "456 Oak Ave", "USER", null, "password456");
    }

    @Nested
    @DisplayName("Tests para el método findAll")
    class FindAllTests {
        @Test
        @DisplayName("Debería devolver 200 y una lista de usuarios")
        void shouldReturnOkAndListOfUsers() throws Exception {
            when(userService.findAllUsers()).thenReturn(List.of(userDto));

            mockMvc.perform(get("/api/users"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].idUser").value(1))
                    .andExpect(jsonPath("$[0].userName").value("johndoe"));
        }
    }

    @Nested
    @DisplayName("Tests para el método getById")
    class GetByIdTests {
        @Test
        @DisplayName("Debería devolver 200 cuando el usuario existe")
        void shouldReturnOkWhenUserExists() throws Exception {
            when(userService.getById(1)).thenReturn(userDto);

            mockMvc.perform(get("/api/users/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.idUser").value(1))
                    .andExpect(jsonPath("$.userName").value("johndoe"));
        }
    }

    @Nested
    @DisplayName("Tests para el método create")
    class CreateTests {
        @Test
        @DisplayName("Debería devolver 201 cuando la petición es válida")
        void shouldReturnCreatedWhenValid() throws Exception {
            UserDto createdUser = new UserDto(2, "Jane Doe", "jane@example.com", "janedoe", 
                "password456", "987654321", "456 Oak Ave", null, "USER");
            when(userService.create(any(UserDto.class))).thenReturn(createdUser);

            mockMvc.perform(post("/api/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userInsertRequest)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.idUser").value(2))
                    .andExpect(jsonPath("$.userName").value("janedoe"));
        }
    }

    @Nested
    @DisplayName("Tests para el método update")
    class UpdateTests {
        @Test
        @DisplayName("Debería devolver 200 cuando la petición es válida")
        void shouldReturnOkWhenValid() throws Exception {
            UserDto updatedUser = new UserDto(1, "John Updated", "john.updated@example.com", 
                "johndoe", "newpassword", "111222333", "789 Pine St", null, "ADMIN");
            when(userService.update(any(UserDto.class))).thenReturn(updatedUser);

            UserInsertRequest updateRequest = new UserInsertRequest(null, "John Updated", 
                "john.updated@example.com", "johndoe", "111222333", "789 Pine St", "ADMIN", null, "newpassword");

            mockMvc.perform(put("/api/users/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateRequest)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.idUser").value(1))
                    .andExpect(jsonPath("$.name").value("John Updated"));
        }
    }

    @Nested
    @DisplayName("Tests para el método delete")
    class DeleteTests {
        @Test
        @DisplayName("Debería devolver 204 cuando el borrado es exitoso")
        void shouldReturnNoContent() throws Exception {
            doNothing().when(userService).delete(anyInt());

            mockMvc.perform(delete("/api/users/1"))
                    .andExpect(status().isNoContent());
        }
    }
}
