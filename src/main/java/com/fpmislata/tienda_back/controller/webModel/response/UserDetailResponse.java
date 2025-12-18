package com.fpmislata.tienda_back.controller.webModel.response;

import java.util.Date;

public record UserDetailResponse(
                String idUser,
                String userName,
                String password,
                String email,
                String name,
                String phoneNumber,
                String address,
                Date birthDate,
                String role

) {
}
