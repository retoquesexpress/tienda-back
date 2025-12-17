package com.fpmislata.tienda_back.controller;

import com.fpmislata.tienda_back.controller.webModel.request.AuthRequest;
import com.fpmislata.tienda_back.controller.webModel.response.AuthResponse;
import com.fpmislata.tienda_back.domain.model.User;
import com.fpmislata.tienda_back.domain.service.AuthService;
import com.fpmislata.tienda_back.domain.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        AuthResponse authResponse = authService.login(request);
        return authResponse;

    }
}