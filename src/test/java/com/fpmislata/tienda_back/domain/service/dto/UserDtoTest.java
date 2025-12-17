package com.fpmislata.tienda_back.domain.service.dto;

import com.fpmislata.tienda_back.domain.repository.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    @Test
    @DisplayName("Test UserDto Creation")
    void testUserDtoCreation() {
        String id_user = "user123";
        String name = "Test User";
        String email = "user@gmail.com";
        String userName = "testuser";
        String password = "password123";
        String phoneNumber = "1234567890";
        String address = "123 Test St";
        Date birthDate = new Date();
        String role = "USER";


        UserEntity userDto = new UserEntity(id_user, name, email, userName, password, phoneNumber, address, birthDate, role);
        assertNotNull(userDto);
        assertEquals(id_user, userDto.id_user());
        assertEquals(name, userDto.name());
        assertEquals(email, userDto.email());
        assertEquals(userName, userDto.userName());
        assertEquals(password, userDto.password());
        assertEquals(phoneNumber, userDto.phoneNumber());
        assertEquals(address, userDto.address());
        assertEquals(birthDate, userDto.birthDate());
        assertEquals(role, userDto.role());
    }

    @Test
    @DisplayName("Test UserDto NotNulls required fields")
    void testUserDtoNotNullsRequiredfields() {
        String id_user = "u1";
        String userName = "user";
        String password = "password123";
        String email = "user@gmail.com";
        String name = "User";
        String role = "USER";

        UserEntity userDto = new UserEntity(id_user, name, email, userName, password, null, null, null, role);
        assertNotNull(userDto.id_user());
    }

    @Test
    @DisplayName("Test UserDto Null Fields")
    void testUserDtoNullFields() {
        String id_user = "u1";
        String userName = "user";
        String password = "password123";
        String email = "user@gmail.com";
        String name = "User";
        String role = "USER";
        UserEntity userDto = new UserEntity(id_user, name, email, userName, password, null, null, null, role);
        assertNull(userDto.address());
        assertNull(userDto.phoneNumber());
        assertNull(userDto.birthDate());
    }

}