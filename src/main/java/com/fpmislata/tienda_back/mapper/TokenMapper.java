package com.fpmislata.tienda_back.mapper;

import com.fpmislata.tienda_back.domain.model.Token;

import java.time.LocalDateTime;

public class TokenMapper {
    private static TokenMapper INSTANCE;

    private TokenMapper() {
    }

    public static TokenMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TokenMapper();
        }
        return INSTANCE;
    }

    public Token fromStringToToken(String tokenString, LocalDateTime expiresAt) {
        return new Token(tokenString, expiresAt);
    }
}
