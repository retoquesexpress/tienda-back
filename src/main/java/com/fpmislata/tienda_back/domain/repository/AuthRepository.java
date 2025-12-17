package com.fpmislata.tienda_back.domain.repository;

import com.fpmislata.tienda_back.domain.repository.entity.UserEntity;

import java.util.Optional;

public interface AuthRepository
{
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findById(String id);
    UserEntity register(UserEntity user);
    boolean existsByUsername(String username);
}
