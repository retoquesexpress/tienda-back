package com.fpmislata.tienda_back.controller.webModel.response;

import com.fpmislata.tienda_back.domain.service.dto.UserDto;

import java.time.LocalDateTime;
import java.util.Date;

public record AuthResponse(
            String token,
            LocalDateTime expirationDate,
            UserDto userDto
) {
}
