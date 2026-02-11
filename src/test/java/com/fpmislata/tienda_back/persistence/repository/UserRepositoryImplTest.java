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
            List<UserJpaEntity> expectedUsers = List.of(
                    new UserJpaEntity(1, "USER1", "user1@gamil.com", "user1", "pass1", "123456789", "Address 1", null, "USER"),
                    new UserJpaEntity(2, "USER2", "user2@gamil.com", "user2", "pass2", "123456459", "Address 2", null, "USER")
            );
            when(userJpaDao.findAllUsers()).thenReturn(expectedUsers);
            List<UserDto> actualUsers = userRepositoryImpl.findAllUsers();
            assertAll(
                    () -> assertEquals(expectedUsers.get(0).getIdUser(), actualUsers.get(0).idUser()),
                    () -> assertEquals(expectedUsers.get(0).getName(), actualUsers.get(0).name()),
                    () -> assertEquals(expectedUsers.get(0).getEmail(), actualUsers.get(0).email()),
                    () -> assertEquals(expectedUsers.get(0).getUserName(), actualUsers.get(0).userName()),
                    () -> assertEquals(expectedUsers.get(0).getPassword(), actualUsers.get(0).password()),
                    () -> assertEquals(expectedUsers.get(0).getPhoneNumber(), actualUsers.get(0).phoneNumber()),
                    () -> assertEquals(expectedUsers.get(0).getAddress(), actualUsers.get(0).address()),
                    () -> assertEquals(expectedUsers.get(0).getRole(), actualUsers.get(0).role()),

                    () -> assertEquals(expectedUsers.get(1).getIdUser(), actualUsers.get(1).idUser()),
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
            when(userJpaDao.findAllUsers()).thenReturn(List.of());
            List<UserDto> actualUsers = userRepositoryImpl.findAllUsers();
            assertTrue(actualUsers.isEmpty());
            verify(userJpaDao).findAllUsers();
        }
    }

    @Nested
    class FindUserById {
        @Test
        @DisplayName("Test findUserById should return user when user exists")
        void testFindUserById_ReturnsUser_WhenUserExists() {
            UserJpaEntity expectedUser = new UserJpaEntity(1, "USER1", "user1@gamil.com", "user1", "pass1", "123456789", "Address 1", null, "USER");
            when(userJpaDao.findUserById(expectedUser.getIdUser())).thenReturn(Optional.of(expectedUser));
            Optional<UserDto> actualUser = userRepositoryImpl.findUserById(expectedUser.getIdUser());
            assertAll(
                    () -> assertEquals(expectedUser.getIdUser(), actualUser.get().idUser()),
                    () -> assertEquals(expectedUser.getName(), actualUser.get().name()),
                    () -> assertEquals(expectedUser.getEmail(), actualUser.get().email()),
                    () -> assertEquals(expectedUser.getUserName(), actualUser.get().userName()),
                    () -> assertEquals(expectedUser.getPassword(), actualUser.get().password()),
                    () -> assertEquals(expectedUser.getPhoneNumber(), actualUser.get().phoneNumber()),
                    () -> assertEquals(expectedUser.getAddress(), actualUser.get().address()),
                    () -> assertEquals(expectedUser.getRole(), actualUser.get().role())
            );
            verify(userJpaDao).findUserById(expectedUser.getIdUser());
        }

        @Test
        @DisplayName("Test findUserById should return empty when user does not exist")
        void testFindUserById_ReturnsEmpty_WhenUserDoesNotExist() {
            Integer userId = 0;
            when(userJpaDao.findUserById(userId)).thenReturn(Optional.empty());
            Optional<UserDto> actualUser = userRepositoryImpl.findUserById(userId);
            assertTrue(actualUser.isEmpty());
            verify(userJpaDao).findUserById(userId);
        }
    }

    @Nested
    class DeleteUser {
        @Test
        @DisplayName("Test delete should delete user")
        void testDelete_CallsUserJpaDaoDeleteMethod_WithCorrectId() {
            Integer userId = 1;
            userRepositoryImpl.delete(userId);
            verify(userJpaDao).delete(userId);
        }
    }

    @Nested
    class getUserById {
        @Test
        @DisplayName("Test getById should return user when user exists")
        void testGetById_ReturnsUser_WhenUserExists() {
            UserJpaEntity expectedUser = new UserJpaEntity(1, "USER1", "user@gmail.com", "user1", "pass1", "123456789", "Address 1", null, "USER");
            when(userJpaDao.findUserById(expectedUser.getIdUser())).thenReturn(Optional.of(expectedUser));
            UserDto actualUser = userRepositoryImpl.getById(expectedUser.getIdUser());
            assertAll(
                    () -> assertEquals(expectedUser.getIdUser(), actualUser.idUser()),
                    () -> assertEquals(expectedUser.getName(), actualUser.name()),
                    () -> assertEquals(expectedUser.getEmail(), actualUser.email()),
                    () -> assertEquals(expectedUser.getUserName(), actualUser.userName()),
                    () -> assertEquals(expectedUser.getPassword(), actualUser.password()),
                    () -> assertEquals(expectedUser.getPhoneNumber(), actualUser.phoneNumber()),
                    () -> assertEquals(expectedUser.getAddress(), actualUser.address()),
                    () -> assertEquals(expectedUser.getRole(), actualUser.role())
            );
            verify(userJpaDao).findUserById(expectedUser.getIdUser());
        }

        @Test
        @DisplayName("Test getById should return null when user does not exist")
        void testGetById_ReturnsNull_WhenUserDoesNotExist() {
            Integer userId = 0;
            when(userJpaDao.findUserById(userId)).thenReturn(Optional.empty());
            UserDto actualUser = userRepositoryImpl.getById(userId);
            assertNull(actualUser);
            verify(userJpaDao).findUserById(userId);
        }

    }

    @Nested
    class FindUserByUserName {
        @Test
        @DisplayName("Test findUserByUserName should return user when user exists")
        void testFindUserByUserName_ReturnsUser_WhenUserExists() {
            UserJpaEntity expectedUser = new UserJpaEntity(1, "USER1", "user@gmail.com", "user1", "pass1", "123456789", "Address 1", null, "USER");
            when(userJpaDao.findUserByUserName(expectedUser.getUserName())).thenReturn(Optional.of(expectedUser));
            Optional<UserDto> actualUser = userRepositoryImpl.findUserByUserName(expectedUser.getUserName());
            assertAll(
                    () -> assertEquals(expectedUser.getIdUser(), actualUser.get().idUser()),
                    () -> assertEquals(expectedUser.getName(), actualUser.get().name()),
                    () -> assertEquals(expectedUser.getEmail(), actualUser.get().email()),
                    () -> assertEquals(expectedUser.getUserName(), actualUser.get().userName()),
                    () -> assertEquals(expectedUser.getPassword(), actualUser.get().password()),
                    () -> assertEquals(expectedUser.getPhoneNumber(), actualUser.get().phoneNumber()),
                    () -> assertEquals(expectedUser.getAddress(), actualUser.get().address()),
                    () -> assertEquals(expectedUser.getRole(), actualUser.get().role())
            );
            verify(userJpaDao).findUserByUserName(expectedUser.getUserName());
        }

        @Test
        @DisplayName("Test findUserByUserName should return empty when user does not exist")
        void testFindUserByUserName_ReturnsEmpty_WhenUserDoesNotExist() {
            String userName = "nonexistent";
            when(userJpaDao.findUserByUserName(userName)).thenReturn(Optional.empty());
            Optional<UserDto> actualUser = userRepositoryImpl.findUserByUserName(userName);
            assertTrue(actualUser.isEmpty());
            verify(userJpaDao).findUserByUserName(userName);
        }
    }


}