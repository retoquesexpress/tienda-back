package com.fpmislata.tienda_back.domain.repository.entity;

import java.util.Date;

public record UserEntity (
        String id_user,
        String name,
        String email,
        String userName,
        String password,
        String phoneNumber,
        String address,
        Date birthDate,
        String role
) {
    public UserEntity(
            String id_user,
            String name,
            String email,
            String userName,
            String password,
            String phoneNumber,
            String address,
            Date birthDate,
            String role
    ) {
        this.id_user = id_user;
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
