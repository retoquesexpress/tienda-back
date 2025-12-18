package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.controller.webModel.request.AuthRequest;
import com.fpmislata.tienda_back.controller.webModel.request.RegisterRequest;
import com.fpmislata.tienda_back.controller.webModel.response.AuthResponse;
import com.fpmislata.tienda_back.domain.model.Token;
import com.fpmislata.tienda_back.domain.model.User;
import com.fpmislata.tienda_back.domain.repository.AuthRepository;
import com.fpmislata.tienda_back.domain.repository.entity.UserEntity;
import com.fpmislata.tienda_back.exception.ResourceNotFoundException;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {
    @Mock
    private AuthRepository authRepository;

    @InjectMocks
    private AuthServiceImpl authServiceImpl;

    @Nested
    class TestGetUserFromToken {
        @Test
        void testGetUserFromToken_shouldThrowException_WhenTokenIsInvalid() {
            //Arrange
            Token token = new Token("invalid.token.string", LocalDateTime.now());
            //Act & Assert
            assertThrows(RuntimeException.class, () -> authServiceImpl.getUserFromToken(token));
        }

    }

    @Nested
    class GenerateTokenForUser {
        @Test
        void testGenerateTokenForUser_shouldReturnToken() {
            //Arrange
            User user = new User("u1", "User1", "user@gmail.com", "user1", "USER1234", "111111111","c667 n77", null, "USER");

            //Act
            Token token = authServiceImpl.generateTokenForUser(user);

            //Assert
            assertNotNull(token);
            assertNotNull(token.getToken());
            assertTrue(token.getExpirationDate().isAfter(LocalDateTime.now()));
        }
    }

    @Nested
    class TestsLogin {
        @Test
        void login_ShouldReturnAuthResponse_WhenCredentialsAreValid() {
            // Arrange
            String username = "user1";
            String password = "USER1234";

            AuthRequest request = new AuthRequest(username, password);

            UserEntity userEntity = new UserEntity(
                    "u1",
                    "User Real Name",
                    "user@gmail.com",
                    username,
                    password,
                    "111111111",
                    "c667 n77",
                    null,
                    "USER"
            );

            when(authRepository.findByUsername(username)).thenReturn(Optional.of(userEntity));

            // Act
            AuthResponse authResponse = authServiceImpl.login(request);

            // Assert
            assertAll(
                    () -> assertNotNull(authResponse),
                    () -> assertNotNull(authResponse.token()),
                    () -> assertNotNull(authResponse.expirationDate()),
                    () -> assertEquals(username, authResponse.userDto().userName()),
                    () -> assertNull(authResponse.userDto().password())
            );

            verify(authRepository, times(1)).findByUsername(username);

        }
        @Test
        @DisplayName("Should throw ResourceNotFoundException when user does not exist")
        void login_ShouldThrowException_WhenUserNotFound() {
            // Arrange
            AuthRequest request = new AuthRequest("unknown", "pass");
            when(authRepository.findByUsername("unknown")).thenReturn(Optional.empty());

            // Act & Assert
            assertThrows(ResourceNotFoundException.class, () -> authServiceImpl.login(request));
        }

        @Test
        @DisplayName("Should throw RuntimeException when password is incorrect")
        void login_ShouldThrowException_WhenPasswordInvalid() {
            // Arrange
            AuthRequest request = new AuthRequest("testUser", "wrongPassword");
            UserEntity userEntity = new UserEntity("1", "Name", "testUser", "correctPassword", "email@test.com", "123", "Addr", null, "USER");

            when(authRepository.findByUsername("testUser")).thenReturn(Optional.of(userEntity));

            // Act & Assert
            RuntimeException exception = assertThrows(RuntimeException.class, () -> authServiceImpl.login(request));
            assertEquals("Invalid credentials", exception.getMessage());
        }

    }

    @Nested
    class TestsRegister {

        @Test
        @DisplayName("Test register should save user and return AuthResponse when username is free")
        void testRegister_ShouldReturnAuthResponse_WhenUsernameDoesNotExist() {
            // Arrange
            RegisterRequest request = new RegisterRequest(

                    "new_user",
                    "pass123",
                    "new@email.com",
                    "USER",
                    "c263",
                    "Main Street 1",
                    null,
                    "USER"
            );
            UserJpaEntity expectedUser = new UserJpaEntity("u1", "USER1", "user1@gamil.com", "user1", "pass1", "123456789", "Address 1", null, "USER");


            when(authRepository.findByUsername(request.userName())).thenReturn(Optional.empty());

            UserEntity savedEntity = new UserEntity(
                    "u100",
                    request.name(),
                    request.email(),
                    request.userName(),
                    request.password(),
                    request.phoneNumber(),
                    request.address(),
                    request.birthDate(),
                    request.role()
            );

            when(authRepository.register(any(UserEntity.class))).thenReturn(savedEntity);

            // Act
            AuthResponse actualResponse = authServiceImpl.register(request);

            // Assert
            assertAll(
                    () -> assertNotNull(actualResponse, "Response should not be null"),
                    () -> assertNotNull(actualResponse.token(), "Token should be generated"),
                    () -> assertEquals(request.userName(), actualResponse.userDto().userName(), "Username should match"),
                    () -> assertEquals("u100", actualResponse.userDto().idUser(), "ID should be the one from saved entity")
            );

            verify(authRepository, times(1)).findByUsername(request.userName());
            verify(authRepository, times(1)).register(any(UserEntity.class));
        }

        @Test
        @DisplayName("Test register should throw RuntimeException when username already exists")
        void testRegister_ShouldThrowRuntimeException_WhenUsernameExists() {
            // Arrange
            RegisterRequest request = new RegisterRequest("existing_user", "pass", "e@e.com", "user", "3333455", "c223", null, "USER");
            UserEntity existingUser = new UserEntity("1", "Name", "testUser", "correctPassword", "email@test.com", "12456783", "Addr", null, "USER");

            when(authRepository.findByUsername(request.userName())).thenReturn(Optional.of(existingUser));

            // Act & Assert
            RuntimeException exception = assertThrows(RuntimeException.class, () -> authServiceImpl.register(request));
            assertEquals("Username already exists", exception.getMessage());

            verify(authRepository, never()).register(any(UserEntity.class));
        }
    }

}