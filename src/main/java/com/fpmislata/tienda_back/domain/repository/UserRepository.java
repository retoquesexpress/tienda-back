package com.fpmislata.tienda_back.domain.repository;

import com.fpmislata.tienda_back.domain.repository.entity.UserEntity;
import com.fpmislata.tienda_back.domain.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<UserEntity> findAllUsers();
    Optional<UserEntity> findUserById(Integer idUser);
    void delete(Integer idUser);
    UserEntity save(UserEntity userEntity);
    UserEntity getById(Integer idUser);
    Optional<UserEntity> findUserByUserName(String userName);
}
