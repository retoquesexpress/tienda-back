package com.fpmislata.tienda_back.domain.repository.entity;

import java.util.Date;

public record UserEntity(
        String idUser,
        String name,
        String email,
        String userName,
        String password,
        String phoneNumber,
        String address,
        Date birthDate,
        String role) {
    public UserEntity(
            String idUser,
            String name,
            String email,
            String userName,
            String password,
            String phoneNumber,
            String address,
            Date birthDate,
            String role) {
        this.idUser = idUser;
        this.name = name;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthDate = birthDate;
        this.role = role;
    }
}
