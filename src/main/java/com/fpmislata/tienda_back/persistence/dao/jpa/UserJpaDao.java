package com.fpmislata.tienda_back.persistence.dao.jpa;

import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;

import java.util.List;
import java.util.Optional;

public interface UserJpaDao {
    List<UserJpaEntity> findAllUsers();

    Optional<UserJpaEntity> findUserById(Integer idUser);

    UserJpaEntity update(UserJpaEntity userDto);

    void delete(Integer idUser);

    UserJpaEntity insert(UserJpaEntity userDto);

    UserJpaEntity getById(Integer idUser);

    Optional<UserJpaEntity> findUserByUserName(String userName);

    Optional<UserJpaEntity> findUserByEmail(String email);
}
