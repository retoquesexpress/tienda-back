package com.fpmislata.tienda_back.domain.service;

import com.fpmislata.tienda_back.controller.webModel.request.AuthRequest;
import com.fpmislata.tienda_back.controller.webModel.request.RegisterRequest;
import com.fpmislata.tienda_back.controller.webModel.response.AuthResponse;
import com.fpmislata.tienda_back.domain.model.Token;
import com.fpmislata.tienda_back.domain.model.User;

public interface AuthService {
    User getUserFromToken(Token token);
    Token generateTokenForUser(User user);
    AuthResponse login(AuthRequest request);
    AuthResponse register(RegisterRequest request);
}
