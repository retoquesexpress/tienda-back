package com.fpmislata.tienda_back.persistence.dao.jpa;

import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;

import java.util.List;
import java.util.Optional;

public interface UserJpaDao {
    List<UserJpaEntity> findAllUsers();
    Optional<UserJpaEntity> findUserById(String id_user);
    UserJpaEntity update(UserJpaEntity userDto);
    void delete(String id_user);
    UserJpaEntity insert(UserJpaEntity userDto);
}
