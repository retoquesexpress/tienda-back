package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.controller.webModel.request.AuthRequest;
import com.fpmislata.tienda_back.controller.webModel.request.RegisterRequest;
import com.fpmislata.tienda_back.controller.webModel.response.AuthResponse;
import com.fpmislata.tienda_back.domain.model.Token;
import com.fpmislata.tienda_back.domain.model.User;
import com.fpmislata.tienda_back.domain.repository.AuthRepository;
import com.fpmislata.tienda_back.domain.repository.entity.UserEntity;
import com.fpmislata.tienda_back.domain.service.AuthService;
import com.fpmislata.tienda_back.domain.service.UserService;
import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import com.fpmislata.tienda_back.mapper.TokenMapper;
import com.fpmislata.tienda_back.mapper.UserMapper;
import com.fpmislata.tienda_back.exception.ResourceNotFoundException;
import com.fpmislata.tienda_back.util.JwtUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;

public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public User getUserFromToken(Token token) {
        if (!JwtUtil.validateToken(token.getToken())) {
            throw new RuntimeException("Invalid or expired token");
        }

        String userId = JwtUtil.extractUserId(token.getToken());

        UserEntity userEntity = authRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserDto userDto = UserMapper.getInstance().fromUserEntityToUserDto(userEntity);
        User user = UserMapper.getInstance().fromUserDtoToUser(userDto);
        return user;
    }

    @Override
    public Token generateTokenForUser(User user) {
        String tokenString = JwtUtil.generateToken(user);
        LocalDateTime expirationDate = JwtUtil.getExpirationTime();
        return TokenMapper.getInstance().fromStringToToken(tokenString, expirationDate);
    }
    @Transactional
    @Override
    public AuthResponse login(AuthRequest request) {
        UserEntity userEntity = authRepository.findByUsername(request.userName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!userEntity.password().equals(request.password())) {
            throw new RuntimeException("Invalid credentials");
        }

        UserDto userDto = UserMapper.getInstance().fromUserEntityToUserDto(userEntity);
        User user = UserMapper.getInstance().fromUserDtoToUser(userDto);

        Token token = generateTokenForUser(user);
        user.setPassword(null);
        AuthResponse authResponse = new AuthResponse(token.getToken(), token.getExpirationDate(), UserMapper.getInstance().fromUserToUserDto(user));

        return authResponse;
    }
    @Transactional
    @Override
    public AuthResponse register(RegisterRequest request) {
        if (authRepository.findByUsername(request.userName()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        UserEntity newUser = new UserEntity(
                null,
                request.userName(),
                request.password(),
                request.email(),
                request.password(),
                request.phoneNumber(),
                request.address(),
                request.birthDate(),
                request.role());
        UserEntity savedUser = authRepository.register(newUser);
        UserDto userDto = UserMapper.getInstance().fromUserEntityToUserDto(savedUser);
        User user = UserMapper.getInstance().fromUserDtoToUser(userDto);
        Token token = generateTokenForUser(user);
        AuthResponse authResponse = new AuthResponse(token.getToken(), token.getExpirationDate(), userDto);
        return authResponse;
    }

    //setpaswrdnull userdto o saveduser
}
