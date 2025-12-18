//package com.fpmislata.tienda_back.persistence.dao.jpa.impl;
//
//import com.fpmislata.tienda_back.domain.service.dto.UserDto;
//import com.fpmislata.tienda_back.persistence.TestConfig;
//import com.fpmislata.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ContextConfiguration;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//@ContextConfiguration(classes = TestConfig.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class UserJpaDaoImplTest {
//
//
//    @Autowired
//    private UserJpaDaoImpl userJpaDao;
//
//    @Test
//    @DisplayName("Test FindUserByID")
//    void testFindUserById() {
//        //Arrange
//        UserJpaEntity user1 = new UserJpaEntity("u1", "User", "user@gmail.com", "user123", "password", "1234567890", "Address", null, "USER");
//        UserJpaEntity result = userJpaDao.insert(user1);
//
//        Optional<UserJpaEntity> actual = userJpaDao.findUserById("u1");
//
//        //Assert
//        assertAll(
//                () -> assertTrue(actual.isPresent()),
//                () -> assertEquals("u1", actual.get().getId_user()),
//                () -> assertEquals("User", actual.get().getName()),
//                () -> assertEquals("user@gmail.com", actual.get().getEmail()),
//                () -> assertEquals("user123", actual.get().getUserName()),
//                () -> assertEquals("password", actual.get().getPassword()),
//                () -> assertEquals("1234567890", actual.get().getPhoneNumber()),
//                () -> assertEquals("Address", actual.get().getAddress()),
//                () -> assertEquals("USER", actual.get().getRole())
//        );
//
//    }
//
//    @Test
//    @DisplayName("Test FindUserByUserName")
//    void testFindUserByUserName() {
//        //Arrange
//        UserJpaEntity user1 = new UserJpaEntity("u1", "User", "user@gmail.com", "user123", "password", "1234567890", "Address", null, "USER");
//        UserJpaEntity result = userJpaDao.insert(user1);
//
//        Optional<UserJpaEntity> actual = userJpaDao.findUserByUserName("user123");
//
//        //Assert
//        assertAll(
//                () -> assertTrue(actual.isPresent()),
//                () -> assertEquals("u1", actual.get().getId_user()),
//                () -> assertEquals("User", actual.get().getName()),
//                () -> assertEquals("user@gmail.com", actual.get().getEmail()),
//                () -> assertEquals("user123", actual.get().getUserName()),
//                () -> assertEquals("password", actual.get().getPassword()),
//                () -> assertEquals("1234567890", actual.get().getPhoneNumber()),
//                () -> assertEquals("Address", actual.get().getAddress()),
//                () -> assertEquals("USER", actual.get().getRole())
//        );
//
//    }
//
//    @Test
//    @DisplayName("Test delete method persists UserJpaEntity")
//    void testDelete() {
//        UserJpaEntity user = new UserJpaEntity();
//        user.setId_user("u1");
//        user.setName("user");
//        user.setUserName("user");
//        user.setEmail("test@test.com");
//        user.setPassword("password");
//        user.setPhoneNumber("1234567890");
//        user.setAddress("Address");
//        user.setRole("USER");
//
//
//        Long totalUsersBeforeDelete = userJpaDao.findAllUsers().stream().count();
//
//        userJpaDao.insert(user);
//        userJpaDao.delete("u1");
//
//        Long totalUsersAfterDelete = userJpaDao.findAllUsers().stream().count();
//
//        assertEquals(totalUsersBeforeDelete, totalUsersAfterDelete);
//    }
//
//    @Test
//    @DisplayName("Test insert method persists UserJpaEntity")
//    void testInsert() {
//        UserJpaEntity newUser = new UserJpaEntity();
//        newUser.setId_user("user1");
//        newUser.setUserName("user");
//        newUser.setEmail("test@test.com");
//        newUser.setPassword("password");
//        newUser.setPhoneNumber("1234567890");
//        newUser.setAddress("Address");
//        newUser.setRole("USER");
//
//
//        UserJpaEntity result = userJpaDao.insert(newUser);
//
//        assertAll(
//                () -> assertNotNull(result),
//                () -> assertEquals("user1", result.getId_user()),
//                () -> assertEquals("testuser", result.getUserName()),
//                () -> assertEquals("test@test.com", result.getEmail()),
//                () -> assertEquals("password", result.getPassword()),
//                () -> assertEquals("1234567890", result.getPhoneNumber()),
//                () -> assertEquals("Address", result.getAddress()),
//                () -> assertEquals("USER", result.getRole())
//        );
//    }
//
//}