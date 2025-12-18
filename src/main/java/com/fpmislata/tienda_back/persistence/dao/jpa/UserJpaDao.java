package com.fpmislata.tienda_back.persistence.dao.jpa;

import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;

import java.util.List;
import java.util.Optional;

public interface UserJpaDao {
    List<UserJpaEntity> findAllUsers();

    Optional<UserJpaEntity> findUserById(String idUser);

    UserJpaEntity update(UserJpaEntity userDto);

    void delete(String idUser);

    UserJpaEntity insert(UserJpaEntity userDto);

    UserJpaEntity getById(String idUser);

    Optional<UserJpaEntity> findUserByUserName(String userName);
}
