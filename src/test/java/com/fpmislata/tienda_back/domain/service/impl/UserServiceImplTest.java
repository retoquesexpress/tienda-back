package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.domain.repository.UserRepository;
import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import com.fpmislata.tienda_back.exception.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Nested
    class TestsFindAllUsers {
        @Test
        @DisplayName("Test findAll should return list of users when users exist")
        void testFindAll_ShouldReturnListOfUsers_WhenUsersExist() {
            // Arrange
            List<UserDto> expectedUsersDto = List.of(
                    new UserDto("u1", "USER1", "user1@gamil.com", "user1", "pass1", "123456789", "Address 1", null, "USER"),
                    new UserDto("u2", "USER2", "user2@gamil.com", "user2", "pass2", "123456459", "Address 2", null, "USER"));

            when(userRepository.findAllUsers()).thenReturn(expectedUsersDto);

            // Act
            List<UserDto> actualUsers = userService.findAllUsers();

            // Assert
            assertAll(
                    () -> assertEquals(expectedUsersDto.get(0).idUser(), actualUsers.get(0).idUser()),
                    () -> assertEquals(expectedUsersDto.get(0).name(), actualUsers.get(0).name()),
                    () -> assertEquals(expectedUsersDto.get(0).email(), actualUsers.get(0).email()),
                    () -> assertEquals(expectedUsersDto.get(0).userName(), actualUsers.get(0).userName()),
                    () -> assertEquals(expectedUsersDto.get(0).password(), actualUsers.get(0).password()),
                    () -> assertEquals(expectedUsersDto.get(0).phoneNumber(), actualUsers.get(0).phoneNumber()),
                    () -> assertEquals(expectedUsersDto.get(0).address(), actualUsers.get(0).address()),
                    () -> assertEquals(expectedUsersDto.get(0).role(), actualUsers.get(0).role()),
                    () -> assertEquals(expectedUsersDto.get(1).idUser(), actualUsers.get(1).idUser()),
                    () -> assertEquals(expectedUsersDto.get(1).name(), actualUsers.get(1).name()),
                    () -> assertEquals(expectedUsersDto.get(1).email(), actualUsers.get(1).email()),
                    () -> assertEquals(expectedUsersDto.get(1).userName(), actualUsers.get(1).userName()),
                    () -> assertEquals(expectedUsersDto.get(1).password(), actualUsers.get(1).password()),
                    () -> assertEquals(expectedUsersDto.get(1).phoneNumber(), actualUsers.get(1).phoneNumber()),
                    () -> assertEquals(expectedUsersDto.get(1).address(), actualUsers.get(1).address()),
                    () -> assertEquals(expectedUsersDto.get(1).role(), actualUsers.get(1).role())



            );
            verify(userRepository, times(1)).findAllUsers();
        }

        @Test
        @DisplayName("Test findAll should return empty list when no users exist")
        void testFindAll_ShouldReturnEmptyList_WhenNoUsersExist() {
            // Arrange
            when(userRepository.findAllUsers()).thenReturn(List.of());
            // Act
            List<UserDto> actualUsers = userService.findAllUsers();
            // Assert
            assertTrue(actualUsers.isEmpty());
            verify(userRepository, times(1)).findAllUsers();
        }
    }

    @Nested
    class TestsFindUserById {
        @Test
        @DisplayName("Test findUserById should return user when user exists")
        void testFindUserById_ShouldReturnUser_WhenUserExists() {
            // Arrange
            String userId = "u1";
            UserDto expectedUserDto = new UserDto("u1", "USER1", "user1@gamil.com", "user1", "pass1", "123456789", "Address 1", null, "USER");
            when(userRepository.findUserById(userId)).thenReturn(Optional.of(expectedUserDto));
            // Act
            Optional<UserDto> actualUser = userService.findUserById(userId);
            // Assert
            assertAll(
                    () -> assertEquals(expectedUserDto.idUser(), actualUser.get().idUser()),
                    () -> assertEquals(expectedUserDto.name(), actualUser.get().name()),
                    () -> assertEquals(expectedUserDto.email(), actualUser.get().email()),
                    () -> assertEquals(expectedUserDto.userName(), actualUser.get().userName()),
                    () -> assertEquals(expectedUserDto.password(), actualUser.get().password()),
                    () -> assertEquals(expectedUserDto.phoneNumber(), actualUser.get().phoneNumber()),
                    () -> assertEquals(expectedUserDto.address(), actualUser.get().address()),
                    () -> assertEquals(expectedUserDto.role(), actualUser.get().role())
            );
            verify(userRepository, times(1)).findUserById(userId);
        }

        @Test
        @DisplayName("Test findUserById should throw ResourceNotFoundException when user does not exist")
        void testFindUserById_ShouldThrowResourceNotFoundException_WhenUserDoesNotExist() {
            // Arrange
            String userId = "u1";
            when(userRepository.findUserById(userId)).thenReturn(Optional.empty());
            // Act & Assert
            assertThrows(ResourceNotFoundException.class, () -> userService.findUserById(userId));
            verify(userRepository, times(1)).findUserById(userId);
        }

    }

    @Nested
    class TestsCreateUser {
        @Test
        @DisplayName("Test create should save and return user when user does not exist")
        void testCreate_ShouldSaveAndReturnUser_WhenUserDoesNotExist() {
            // Arrange
            UserDto expectedUserDto = new UserDto("u1", "USER1", "user1@gamil.com", "user1", "pass1", "123456789", "Address 1", null, "USER");

            when(userRepository.save(expectedUserDto)).thenReturn(expectedUserDto);

            // Act
            UserDto actualUser = userService.create(expectedUserDto);

            // Assert
            assertAll(
                    () -> assertEquals(expectedUserDto.idUser(), actualUser.idUser()),
                    () -> assertEquals(expectedUserDto.name(), actualUser.name()),
                    () -> assertEquals(expectedUserDto.email(), actualUser.email()),
                    () -> assertEquals(expectedUserDto.userName(), actualUser.userName()),
                    () -> assertEquals(expectedUserDto.password(), actualUser.password()),
                    () -> assertEquals(expectedUserDto.phoneNumber(), actualUser.phoneNumber()),
                    () -> assertEquals(expectedUserDto.address(), actualUser.address()),
                    () -> assertEquals(expectedUserDto.role(), actualUser.role())
            );
            verify(userRepository).save(expectedUserDto);

        }

        @Test
        @DisplayName("Test create should throw IllegalArgumentException when user already exists")
        void testCreate_ShouldThrowIllegalArgumentException_WhenUserAlreadyExists() {
            // Arrange
            UserDto expectedUserDto = new UserDto("u1", "USER1", "user1@gamil.com", "user1", "pass1", "123456789", "Address 1", null, "USER");
            when(userRepository.findUserById(expectedUserDto.idUser())).thenReturn(Optional.of(expectedUserDto));
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> userService.create(expectedUserDto));
            verify(userRepository).findUserById(expectedUserDto.idUser());
        }
    }

    @Nested
    class TestsUpdateUser {
        @Test
        @DisplayName("Test update should modify and return user when user exists")
        void testUpdate_ShouldModifyAndReturnUser_WhenUserExists() {
            // Arrange
            String userId = "u1";
            UserDto expectedUserDto = new UserDto("u1", "USER1", "user1@gamil.com", "user1", "pass1", "123456789", "Address 1", null, "USER");
            when(userRepository.findUserById(userId)).thenReturn(Optional.of(expectedUserDto));
            //Act
            Optional<UserDto> actualUser = userService.findUserById(userId);
            // Assert
            assertAll(
                    () -> assertEquals(expectedUserDto.idUser(), actualUser.get().idUser()),
                    () -> assertEquals(expectedUserDto.name(), actualUser.get().name()),
                    () -> assertEquals(expectedUserDto.email(), actualUser.get().email()),
                    () -> assertEquals(expectedUserDto.userName(), actualUser.get().userName()),
                    () -> assertEquals(expectedUserDto.password(), actualUser.get().password()),
                    () -> assertEquals(expectedUserDto.phoneNumber(), actualUser.get().phoneNumber()),
                    () -> assertEquals(expectedUserDto.address(), actualUser.get().address()),
                    () -> assertEquals(expectedUserDto.role(), actualUser.get().role())
            );
            verify(userRepository).findUserById(userId);
        }

        @Test
        @DisplayName("Test update should throw ResourceNotFoundException when user does not exist")
        void testUpdate_ShouldThrowResourceNotFoundException_WhenUserDoesNotExist() {
            // Arrange
            String userId = "u1";
            when(userRepository.findUserById(userId)).thenReturn(Optional.empty());
            // Act & Assert
            assertThrows(ResourceNotFoundException.class, () -> userService.findUserById(userId));
            verify(userRepository).findUserById(userId);

        }
    }

    @Nested
    class TestsDeleteUser {
        @Test
        @DisplayName("Test delete should remove user when user exists")
        void testDelete_ShouldRemoveUser_WhenUserExists() {
            // Arrange
            String userId = "u1";
            UserDto expectedUserDto = new UserDto("u1", "USER1", "user1@gamil.com", "user1", "pass1", "123456789", "Address 1", null, "USER");
            when(userRepository.findUserById(userId)).thenReturn(Optional.of(expectedUserDto));
            // Act
            userService.delete(userId);
            // Assert
            verify(userRepository).delete(userId);
        }

        @Test
        @DisplayName("Test delete should throw ResourceNotFoundException when user does not exist")
        void testDelete_ShouldThrowResourceNotFoundException_WhenUserDoesNotExist() {
            // Arrange
            String userId = "u1";
            when(userRepository.findUserById(userId)).thenReturn(Optional.empty());
            // Act & Assert
            assertThrows(ResourceNotFoundException.class, () -> userService.delete(userId));
            verify(userRepository).findUserById(userId);
        }
    }

    @Nested
    class TestsGetById {
        @Test
        @DisplayName("Test getById should return user when user exists")
        void testGetById_ShouldReturnUser_WhenUserExists() {
            // Arrange
            String userId = "u1";
            UserDto expectedUserDto = new UserDto("u1", "USER1", "user1@gamil.com", "user1", "pass1", "123456789", "Address 1", null, "USER");
            when(userRepository.findUserById(userId)).thenReturn(Optional.of(expectedUserDto));
            // Act
            UserDto actualUser = userService.getById(userId);
            // Assert
            assertAll(
                    () -> assertEquals(expectedUserDto.idUser(), actualUser.idUser()),
                    () -> assertEquals(expectedUserDto.name(), actualUser.name()),
                    () -> assertEquals(expectedUserDto.email(), actualUser.email()),
                    () -> assertEquals(expectedUserDto.userName(), actualUser.userName()),
                    () -> assertEquals(expectedUserDto.password(), actualUser.password()),
                    () -> assertEquals(expectedUserDto.phoneNumber(), actualUser.phoneNumber()),
                    () -> assertEquals(expectedUserDto.address(), actualUser.address()),
                    () -> assertEquals(expectedUserDto.role(), actualUser.role())
            );
            verify(userRepository).findUserById(userId);
        }

        @Test
        @DisplayName("Test getById should throw ResourceNotFoundException when user does not exist")
        void testGetById_ShouldThrowResourceNotFoundException_WhenUserDoesNotExist() {
            // Arrange
            String userId = "u1";
            when(userRepository.findUserById(userId)).thenReturn(Optional.empty());
            // Act & Assert
            assertThrows(ResourceNotFoundException.class, () -> userService.getById(userId));
            verify(userRepository).findUserById(userId);
        }
    }

    @Nested
    class TestsFindUserByUserName {
        @Test
        @DisplayName("Test findUserByUserName should return user when user exists")
        void testFindUserByUserName_ShouldReturnUser_WhenUserExists() {
            // Arrange
            String userName = "user1";
            UserDto expectedUserDto = new UserDto("u1", "USER1", "user@gmail.com", "user1", "pass1", "123456789", "Address 1", null, "USER");
            when(userRepository.findUserByUserName(userName)).thenReturn(Optional.of(expectedUserDto));
            // Act
            Optional<UserDto> actualUser = userService.findUserByUserName(userName);
            // Assert
            assertAll(
                    () -> assertEquals(expectedUserDto.idUser(), actualUser.get().idUser()),
                    () -> assertEquals(expectedUserDto.name(), actualUser.get().name()),
                    () -> assertEquals(expectedUserDto.email(), actualUser.get().email()),
                    () -> assertEquals(expectedUserDto.userName(), actualUser.get().userName()),
                    () -> assertEquals(expectedUserDto.password(), actualUser.get().password()),
                    () -> assertEquals(expectedUserDto.phoneNumber(), actualUser.get().phoneNumber()),
                    () -> assertEquals(expectedUserDto.address(), actualUser.get().address()),
                    () -> assertEquals(expectedUserDto.role(), actualUser.get().role())
            );
            verify(userRepository).findUserByUserName(userName);

        }
        @Test
        @DisplayName("Test findUserByUserName should throw ResourceNotFoundException when user does not exist")
        void testFindUserByUserName_ShouldThrowResourceNotFoundException_WhenUserDoesNotExist() {
            // Arrange
            String userName = "user1";
            when(userRepository.findUserByUserName(userName)).thenReturn(Optional.empty());
            // Act & Assert
            assertThrows(ResourceNotFoundException.class, () -> userService.findUserByUserName(userName));
            verify(userRepository).findUserByUserName(userName);
        }
    }

}