package com.fpmislata.tienda_back.domain.service.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UserDto (
        @NotNull
        String id_user,
        @NotNull
        String name,
        @NotNull
        String email,
        @NotNull
        String userName,
        @NotNull
        String password,
        String phoneNumber,
        String address,
        Date birthDate,
        @NotNull
        String role
) {}


