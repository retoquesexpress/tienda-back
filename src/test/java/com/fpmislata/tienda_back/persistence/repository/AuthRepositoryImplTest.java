package com.fpmislata.tienda_back.persistence.repository;

import com.fpmislata.tienda_back.domain.repository.entity.UserEntity;
import com.fpmislata.tienda_back.persistence.dao.jpa.UserJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthRepositoryImplTest {

    @Mock
    private UserJpaDao userJpaDao;

    @InjectMocks
    private AuthRepositoryImpl authRepository;

    private UserJpaEntity userJpaEntity;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userJpaEntity = new UserJpaEntity(1, "Test User", "test@example.com", "testuser",
                "password123", "123456789", "Test Address", null, "USER");
        
        userEntity = new UserEntity(1, "Test User", "test@example.com", "testuser",
                "password123", "123456789", "Test Address", null, "USER");
    }

    @Nested
    @DisplayName("Tests para el método findByUsername")
    class FindByUsernameTests {
        @Test
        @DisplayName("Debería devolver UserEntity cuando el usuario existe")
        void shouldReturnUserEntityWhenUserExists() {
            when(userJpaDao.findUserByUserName("testuser")).thenReturn(Optional.of(userJpaEntity));

            Optional<UserEntity> result = authRepository.findByUsername("testuser");

            assertTrue(result.isPresent());
            assertEquals("testuser", result.get().userName());
            assertEquals("test@example.com", result.get().email());
            verify(userJpaDao, times(1)).findUserByUserName("testuser");
        }

        @Test
        @DisplayName("Debería devolver Optional vacío cuando el usuario no existe")
        void shouldReturnEmptyOptionalWhenUserDoesNotExist() {
            when(userJpaDao.findUserByUserName("nonexistent")).thenReturn(Optional.empty());

            Optional<UserEntity> result = authRepository.findByUsername("nonexistent");

            assertFalse(result.isPresent());
            verify(userJpaDao, times(1)).findUserByUserName("nonexistent");
        }
    }

    @Nested
    @DisplayName("Tests para el método findById")
    class FindByIdTests {
        @Test
        @DisplayName("Debería devolver UserEntity cuando el ID existe")
        void shouldReturnUserEntityWhenIdExists() {
            when(userJpaDao.findUserById(1)).thenReturn(Optional.of(userJpaEntity));

            Optional<UserEntity> result = authRepository.findById(1);

            assertTrue(result.isPresent());
            assertEquals(1, result.get().idUser());
            assertEquals("Test User", result.get().name());
            verify(userJpaDao, times(1)).findUserById(1);
        }

        @Test
        @DisplayName("Debería devolver Optional vacío cuando el ID no existe")
        void shouldReturnEmptyOptionalWhenIdDoesNotExist() {
            when(userJpaDao.findUserById(999)).thenReturn(Optional.empty());

            Optional<UserEntity> result = authRepository.findById(999);

            assertFalse(result.isPresent());
            verify(userJpaDao, times(1)).findUserById(999);
        }
    }

    @Nested
    @DisplayName("Tests para el método register")
    class RegisterTests {
        @Test
        @DisplayName("Debería registrar un nuevo usuario correctamente")
        void shouldRegisterNewUserSuccessfully() {
            when(userJpaDao.insert(any(UserJpaEntity.class))).thenReturn(userJpaEntity);

            UserEntity result = authRepository.register(userEntity);

            assertNotNull(result);
            assertEquals("testuser", result.userName());
            assertEquals("test@example.com", result.email());
            verify(userJpaDao, times(1)).insert(any(UserJpaEntity.class));
        }
    }

    @Nested
    @DisplayName("Tests para el método existsByUsername")
    class ExistsByUsernameTests {
        @Test
        @DisplayName("Debería devolver true cuando el usuario existe")
        void shouldReturnTrueWhenUsernameExists() {
            when(userJpaDao.findUserByUserName("testuser")).thenReturn(Optional.of(userJpaEntity));

            boolean result = authRepository.existsByUsername("testuser");

            assertTrue(result);
            verify(userJpaDao, times(1)).findUserByUserName("testuser");
        }

        @Test
        @DisplayName("Debería devolver false cuando el usuario no existe")
        void shouldReturnFalseWhenUsernameDoesNotExist() {
            when(userJpaDao.findUserByUserName("nonexistent")).thenReturn(Optional.empty());

            boolean result = authRepository.existsByUsername("nonexistent");

            assertFalse(result);
            verify(userJpaDao, times(1)).findUserByUserName("nonexistent");
        }
    }

    @Nested
    @DisplayName("Tests para el método findByEmail")
    class FindByEmailTests {
        @Test
        @DisplayName("Debería devolver UserEntity cuando el email existe")
        void shouldReturnUserEntityWhenEmailExists() {
            when(userJpaDao.findUserByEmail("test@example.com")).thenReturn(Optional.of(userJpaEntity));

            Optional<UserEntity> result = authRepository.findByEmail("test@example.com");

            assertTrue(result.isPresent());
            assertEquals("test@example.com", result.get().email());
            assertEquals("testuser", result.get().userName());
            verify(userJpaDao, times(1)).findUserByEmail("test@example.com");
        }

        @Test
        @DisplayName("Debería devolver Optional vacío cuando el email no existe")
        void shouldReturnEmptyOptionalWhenEmailDoesNotExist() {
            when(userJpaDao.findUserByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

            Optional<UserEntity> result = authRepository.findByEmail("nonexistent@example.com");

            assertFalse(result.isPresent());
            verify(userJpaDao, times(1)).findUserByEmail("nonexistent@example.com");
        }
    }
}
