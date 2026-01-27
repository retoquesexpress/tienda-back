package com.fpmislata.tienda_back.persistence.dao.jpa.impl;

import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import com.fpmislata.tienda_back.persistence.TestConfig;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class UserJpaDaoImplTest {


    @Autowired
    private UserJpaDaoImpl userJpaDao;

    @Test
    @DisplayName("Test FindUserByID")
    void testFindUserById() {
        //Arrange
        UserJpaEntity user1 = new UserJpaEntity(null, "User", "user@gmail.com", "user123", "password", "1234567890", "Address", null, "USER");
        UserJpaEntity inserted = userJpaDao.insert(user1);
        Integer generatedId = inserted.getIdUser();

        Optional<UserJpaEntity> actual = userJpaDao.findUserById(generatedId);

        //Assert
        assertAll(
                () -> assertTrue(actual.isPresent()),
                () -> assertEquals(generatedId, actual.get().getIdUser()),
                () -> assertEquals("User", actual.get().getName()),
                () -> assertEquals("user@gmail.com", actual.get().getEmail()),
                () -> assertEquals("user123", actual.get().getUserName()),
                () -> assertEquals("password", actual.get().getPassword()),
                () -> assertEquals("1234567890", actual.get().getPhoneNumber()),
                () -> assertEquals("Address", actual.get().getAddress()),
                () -> assertEquals("USER", actual.get().getRole())
        );

    }

    @Test
    @DisplayName("Test FindUserByUserName")
    void testFindUserByUserName() {
        //Arrange
        UserJpaEntity user1 = new UserJpaEntity(null, "User", "user@gmail.com", "user123", "password", "1234567890", "Address", null, "USER");
        UserJpaEntity inserted = userJpaDao.insert(user1);
        Integer generatedId = inserted.getIdUser();

        Optional<UserJpaEntity> actual = userJpaDao.findUserByUserName("user123");

        //Assert
        assertAll(
                () -> assertTrue(actual.isPresent()),
                () -> assertEquals(generatedId, actual.get().getIdUser()),
                () -> assertEquals("User", actual.get().getName()),
                () -> assertEquals("user@gmail.com", actual.get().getEmail()),
                () -> assertEquals("user123", actual.get().getUserName()),
                () -> assertEquals("password", actual.get().getPassword()),
                () -> assertEquals("1234567890", actual.get().getPhoneNumber()),
                () -> assertEquals("Address", actual.get().getAddress()),
                () -> assertEquals("USER", actual.get().getRole())
        );

    }

    @Test
    @DisplayName("Test delete method persists UserJpaEntity")
    void testDelete() {
        UserJpaEntity user = new UserJpaEntity();
        user.setName("user");
        user.setUserName("user_delete");
        user.setEmail("test_delete@test.com");
        user.setPassword("password");
        user.setPhoneNumber("1234567890");
        user.setAddress("Address");
        user.setRole("USER");


        Long totalUsersBefore = (long) userJpaDao.findAllUsers().size();

        UserJpaEntity inserted = userJpaDao.insert(user);
        Integer generatedId = inserted.getIdUser();

        userJpaDao.delete(generatedId);

        Long totalUsersAfter = (long) userJpaDao.findAllUsers().size();

        assertEquals(totalUsersBefore, totalUsersAfter);
    }

    @Test
    @DisplayName("Test insert method persists UserJpaEntity")
    void testInsert() {
        UserJpaEntity newUser = new UserJpaEntity();
        newUser.setName("Test User");
        newUser.setUserName("user_insert");
        newUser.setEmail("test_insert@test.com");
        newUser.setPassword("password");
        newUser.setPhoneNumber("1234567890");
        newUser.setAddress("Address");
        newUser.setRole("USER");


        UserJpaEntity result = userJpaDao.insert(newUser);

        assertAll(
                () -> assertNotNull(result),
                () -> assertNotNull(result.getIdUser()),
                () -> assertEquals("user_insert", result.getUserName()),
                () -> assertEquals("test_insert@test.com", result.getEmail()),
                () -> assertEquals("password", result.getPassword()),
                () -> assertEquals("1234567890", result.getPhoneNumber()),
                () -> assertEquals("Address", result.getAddress()),
                () -> assertEquals("USER", result.getRole())
        );
    }

    @Test
    @DisplayName("Test findAllUsers method returns all users")
    void testFindAllUsers() {
        List<UserJpaEntity> result = userJpaDao.findAllUsers();
        assertAll(
                () -> assertNotNull(result),
                () -> assertTrue(result.size() > 0)
        );
    }

    @Test
    @DisplayName("Test find user by email")
    void testFindUserByEmail() {
        UserJpaEntity user = new UserJpaEntity();
        user.setName("user");
        user.setUserName("user_email");
        user.setEmail("test_email@test.com");
        user.setPassword("password");
        user.setPhoneNumber("1234567890");
        user.setAddress("Address");
        user.setRole("USER");


        UserJpaEntity inserted = userJpaDao.insert(user);
        Integer generatedId = inserted.getIdUser();

        Optional<UserJpaEntity> actual = userJpaDao.findUserByEmail("test_email@test.com");

        assertAll(
                () -> assertTrue(actual.isPresent()),
                () -> assertEquals(generatedId, actual.get().getIdUser()),
                () -> assertEquals("user", actual.get().getName()),
                () -> assertEquals("user_email", actual.get().getUserName()),
                () -> assertEquals("test_email@test.com", actual.get().getEmail()),
                () -> assertEquals("password", actual.get().getPassword()),
                () -> assertEquals("1234567890", actual.get().getPhoneNumber()),
                () -> assertEquals("Address", actual.get().getAddress()),
                () -> assertEquals("USER", actual.get().getRole())
        );
    }

    @Test
    @DisplayName("Test update method updates UserJpaEntity")
    void testUpdate() {
        UserJpaEntity user = new UserJpaEntity();
        user.setName("user");
        user.setUserName("user_update");
        user.setEmail("test_update@test.com");
        user.setPassword("password");
        user.setPhoneNumber("1234567890");
        user.setAddress("Address");
        user.setRole("USER");


        UserJpaEntity inserted = userJpaDao.insert(user);
        Integer generatedId = inserted.getIdUser();

        UserJpaEntity updated = new UserJpaEntity();
        updated.setIdUser(generatedId);
        updated.setName("user_update");
        updated.setUserName("user_update");
        updated.setEmail("test_update@test.com");
        updated.setPassword("password");
        updated.setPhoneNumber("1234567890");
        updated.setAddress("Address");
        updated.setRole("USER");


        UserJpaEntity result = userJpaDao.update(updated);

        assertAll(
                () -> assertNotNull(result),
                () -> assertNotNull(result.getIdUser()),
                () -> assertEquals("user_update", result.getUserName()),
                () -> assertEquals("test_update@test.com", result.getEmail()),
                () -> assertEquals("password", result.getPassword()),
                () -> assertEquals("1234567890", result.getPhoneNumber()),
                () -> assertEquals("Address", result.getAddress()),
                () -> assertEquals("USER", result.getRole())
        );
    }

    @Test
    @DisplayName("Test get by id method returns UserJpaEntity")
    void testGetById() {
        UserJpaEntity user = new UserJpaEntity();
        user.setName("user");
        user.setUserName("user_get");
        user.setEmail("test_get@test.com");
        user.setPassword("password");
        user.setPhoneNumber("1234567890");
        user.setAddress("Address");
        user.setRole("USER");


        UserJpaEntity inserted = userJpaDao.insert(user);
        Integer generatedId = inserted.getIdUser();

        UserJpaEntity result = userJpaDao.getById(generatedId);

        assertAll(
                () -> assertNotNull(result),
                () -> assertNotNull(result.getIdUser()),
                () -> assertEquals("user_get", result.getUserName()),
                () -> assertEquals("test_get@test.com", result.getEmail()),
                () -> assertEquals("password", result.getPassword()),
                () -> assertEquals("1234567890", result.getPhoneNumber()),
                () -> assertEquals("Address", result.getAddress()),
                () -> assertEquals("USER", result.getRole())
        );
    }


}
