package com.fpmislata.tienda_back.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpmislata.tienda_back.controller.webModel.request.AuthRequest;
import com.fpmislata.tienda_back.controller.webModel.request.RegisterRequest;
import com.fpmislata.tienda_back.controller.webModel.response.AuthResponse;
import com.fpmislata.tienda_back.domain.service.AuthService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private AuthRequest authRequest;
    private RegisterRequest registerRequest;
    private AuthResponse authResponse;

    @BeforeEach
    void setUp() {
        authRequest = new AuthRequest("testuser", "password123");
        registerRequest = new RegisterRequest("testuser", "password123", "test@example.com", 
            "Test User", "123456789", "Test Address", null, "USER");
        
        UserDto userDto = new UserDto(1, "Test User", "test@example.com", "testuser", 
            "password123", "123456789", "Test Address", null, "USER");
        authResponse = new AuthResponse("test-jwt-token", null, userDto);
    }

    @Nested
    @DisplayName("Tests para el método login")
    class LoginTests {
        @Test
        @DisplayName("Debería devolver 200 y un token cuando las credenciales son válidas")
        void shouldReturnOkWhenCredentialsAreValid() throws Exception {
            when(authService.login(any(AuthRequest.class))).thenReturn(authResponse);

            mockMvc.perform(post("/api/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(authRequest)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.token").value("test-jwt-token"))
                    .andExpect(jsonPath("$.userDto.userName").value("testuser"));
        }

        @Test
        @DisplayName("Debería devolver 404 cuando el usuario no existe")
        void shouldReturnNotFoundWhenUserDoesNotExist() throws Exception {
            when(authService.login(any(AuthRequest.class)))
                    .thenThrow(new RuntimeException("User not found"));

            mockMvc.perform(post("/api/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(authRequest)))
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Debería devolver 401 cuando las credenciales son inválidas")
        void shouldReturnUnauthorizedWhenCredentialsAreInvalid() throws Exception {
            when(authService.login(any(AuthRequest.class)))
                    .thenThrow(new RuntimeException("Invalid credentials"));

            mockMvc.perform(post("/api/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(authRequest)))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("Debería devolver 500 cuando ocurre un error interno")
        void shouldReturnInternalServerErrorWhenExceptionOccurs() throws Exception {
            when(authService.login(any(AuthRequest.class)))
                    .thenThrow(new RuntimeException("Database error"));

            mockMvc.perform(post("/api/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(authRequest)))
                    .andExpect(status().isInternalServerError());
        }
    }

    @Nested
    @DisplayName("Tests para el método register")
    class RegisterTests {
        @Test
        @DisplayName("Debería devolver 201 cuando el registro es exitoso")
        void shouldReturnCreatedWhenRegistrationIsSuccessful() throws Exception {
            when(authService.register(any(RegisterRequest.class))).thenReturn(authResponse);

            mockMvc.perform(post("/api/auth/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(registerRequest)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.token").value("test-jwt-token"))
                    .andExpect(jsonPath("$.userDto.userName").value("testuser"));
        }

        @Test
        @DisplayName("Debería devolver 400 cuando el registro falla")
        void shouldReturnBadRequestWhenRegistrationFails() throws Exception {
            when(authService.register(any(RegisterRequest.class)))
                    .thenThrow(new RuntimeException("Username already exists"));

            mockMvc.perform(post("/api/auth/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(registerRequest)))
                    .andExpect(status().isBadRequest());
        }
    }
}
