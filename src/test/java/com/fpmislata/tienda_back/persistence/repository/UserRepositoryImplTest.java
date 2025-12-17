package com.fpmislata.tienda_back.persistence.repository;

import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import com.fpmislata.tienda_back.persistence.dao.jpa.UserJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImplTest {

    @Mock
    private UserJpaDao userJpaDao;

    @InjectMocks
    private UserRepositoryImpl userRepositoryImpl;

    @Nested
    class FindAllUsers {
        @Test
        @DisplayName("Test findAllUsers should return list of users when users exist")
        void testFindAllUsers_ReturnsListOfUsers_WhenUsersExist() {
            //Arrange
            List<UserJpaEntity> expectedUsers = List.of(
                    new UserJpaEntity("u1", "USER1", "user1@gamil.com", "user1", "pass1", "123456789", "Address 1", null, "USER"),
                    new UserJpaEntity("u2", "USER2", "user2@gamil.com", "user2", "pass2", "123456459", "Address 2", null, "USER")
            );
            when(userJpaDao.findAllUsers()).thenReturn(expectedUsers);
            //Act
            List<UserDto> actualUsers = userRepositoryImpl.findAllUsers();
            //Assert
            assertAll(
                    () -> assertEquals(expectedUsers.get(0).getId_user(), actualUsers.get(0).id_user()),
                    () -> assertEquals(expectedUsers.get(0).getName(), actualUsers.get(0).name()),
                    () -> assertEquals(expectedUsers.get(0).getEmail(), actualUsers.get(0).email()),
                    () -> assertEquals(expectedUsers.get(0).getUserName(), actualUsers.get(0).userName()),
                    () -> assertEquals(expectedUsers.get(0).getPassword(), actualUsers.get(0).password()),
                    () -> assertEquals(expectedUsers.get(0).getPhoneNumber(), actualUsers.get(0).phoneNumber()),
                    () -> assertEquals(expectedUsers.get(0).getAddress(), actualUsers.get(0).address()),
                    () -> assertEquals(expectedUsers.get(0).getRole(), actualUsers.get(0).role()),

                    () -> assertEquals(expectedUsers.get(1).getId_user(), actualUsers.get(1).id_user()),
                    () -> assertEquals(expectedUsers.get(1).getName(), actualUsers.get(1).name()),
                    () -> assertEquals(expectedUsers.get(1).getEmail(), actualUsers.get(1).email()),
                    () -> assertEquals(expectedUsers.get(1).getUserName(), actualUsers.get(1).userName()),
                    () -> assertEquals(expectedUsers.get(1).getPassword(), actualUsers.get(1).password()),
                    () -> assertEquals(expectedUsers.get(1).getPhoneNumber(), actualUsers.get(1).phoneNumber()),
                    () -> assertEquals(expectedUsers.get(1).getAddress(), actualUsers.get(1).address()),
                    () -> assertEquals(expectedUsers.get(1).getRole(), actualUsers.get(1).role())
            );
            verify(userJpaDao).findAllUsers();
        }

        @Test
        @DisplayName("Test findAllUsers should return empty list when no users exist")
        void testFindAllUsers_ReturnsEmptyList_WhenNoUsersExist() {
            //Arrange
            when(userJpaDao.findAllUsers()).thenReturn(List.of());
            //Act
            List<UserDto> actualUsers = userRepositoryImpl.findAllUsers();
            //Assert
            assertTrue(actualUsers.isEmpty());
            verify(userJpaDao).findAllUsers();
        }
    }

    @Nested
    class FindUserById {
        @Test
        @DisplayName("Test findUserById should return user when user exists")
        void testFindUserById_ReturnsUser_WhenUserExists() {
            //Arrange
            UserJpaEntity expectedUser = new UserJpaEntity("u1", "USER1", "user1@gamil.com", "user1", "pass1", "123456789", "Address 1", null, "USER");
            when(userJpaDao.findUserById(expectedUser.getId_user())).thenReturn(Optional.of(expectedUser));
            //Act
            Optional<UserDto> actualUser = userRepositoryImpl.findUserById(expectedUser.getId_user());
            //Assert
            assertAll(
                    () -> assertEquals(expectedUser.getId_user(), actualUser.get().id_user()),
                    () -> assertEquals(expectedUser.getName(), actualUser.get().name()),
                    () -> assertEquals(expectedUser.getEmail(), actualUser.get().email()),
                    () -> assertEquals(expectedUser.getUserName(), actualUser.get().userName()),
                    () -> assertEquals(expectedUser.getPassword(), actualUser.get().password()),
                    () -> assertEquals(expectedUser.getPhoneNumber(), actualUser.get().phoneNumber()),
                    () -> assertEquals(expectedUser.getAddress(), actualUser.get().address()),
                    () -> assertEquals(expectedUser.getRole(), actualUser.get().role())
            );
            verify(userJpaDao).findUserById(expectedUser.getId_user());
        }

        @Test
        @DisplayName("Test findUserById should return empty when user does not exist")
        void testFindUserById_ReturnsEmpty_WhenUserDoesNotExist() {
            //Arrange
            String userId = "nonexistent";
            when(userJpaDao.findUserById(userId)).thenReturn(Optional.empty());
            //Act
            Optional<UserDto> actualUser = userRepositoryImpl.findUserById(userId);
            //Assert
            assertTrue(actualUser.isEmpty());
            verify(userJpaDao).findUserById(userId);
        }
    }

    @Nested
    class DeleteUser {
        @Test
        @DisplayName("Test delete should delete user")
        void testDelete_CallsUserJpaDaoDeleteMethod_WithCorrectId() {
            //Arrange
            String userId = "u1";
            //Act
            userRepositoryImpl.delete(userId);
            //Assert
            verify(userJpaDao).delete(userId);
        }
    }

}