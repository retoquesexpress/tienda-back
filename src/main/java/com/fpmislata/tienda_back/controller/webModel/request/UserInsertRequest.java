package com.fpmislata.tienda_back.controller.webModel.request;

import java.util.Date;

public record UserInsertRequest (
        String id_user,
        String userName,
        String password,
        String email,
        String name,
        String phoneNumber,
        String address,
        Date birthDate,
        String role
){
}
