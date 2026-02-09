package com.fpmislata.tienda_back.pay.Dto;

import java.time.LocalDate;

public record OrigenDto(
        String cardNumber,
        LocalDate expirationDate,
        Integer cvv,
        String nombreCompleto
) {
}
