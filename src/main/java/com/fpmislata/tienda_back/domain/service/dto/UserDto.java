package com.fpmislata.tienda_back.domain.service.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UserDto (
        String id_user,
        String name,
        String email,
        String userName,
        String password,
        String phoneNumber,
        String address,
        Date birthDate,
        String role
) {}


