package com.fpmislata.tienda_back.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    @DisplayName("Test User Constructor and Getters")
    void testUserConstructorAndGetters() {
        User user = new User("u1", "User", "User@gmail.com", "user", "user1234", "123456789", "123 Street", null, "USER");
        assertEquals("u1", user.getId());
        assertEquals("User", user.getName());
        assertEquals("User@gmail.com", user.getEmail());
        assertEquals("user", user.getUserName());
        assertEquals("user1234", user.getPassword());
        assertEquals("123456789", user.getPhoneNumber());
        assertEquals("123 Street", user.getAddress());
        assertNull(user.getBirthDate());
        assertEquals("USER", user.getRole());

    }

    @Test
    @DisplayName("Test User Setters")
    void testUserSetters() {
        User user = new User("u1", "User", "User@gmail.com", "user", "user1234", "123456789", "123 Street", null, "USER");
        user.setId("u2");
        user.setName("Updated User");
        user.setEmail("updatedEmail@gmail.com");
        user.setUserName("updatedUser");
        user.setPassword("updatedPass1234");
        user.setPhoneNumber("987654321");
        user.setAddress("456 Avenue");
        user.setBirthDate(null);
        user.setRole("ADMIN");

        assertEquals("u2", user.getId());
        assertEquals("Updated User", user.getName());
        assertEquals("updatedEmail@gmail.com", user.getEmail());
        assertEquals("updatedUser", user.getUserName());
        assertEquals("updatedPass1234", user.getPassword());
        assertEquals("987654321", user.getPhoneNumber());
        assertEquals("456 Avenue", user.getAddress());
        assertNull(user.getBirthDate());
        assertEquals("ADMIN", user.getRole());
    }

}