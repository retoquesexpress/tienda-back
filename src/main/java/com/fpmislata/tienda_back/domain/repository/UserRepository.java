package com.fpmislata.tienda_back.domain.repository;

import com.fpmislata.tienda_back.domain.repository.entity.UserEntity;
import com.fpmislata.tienda_back.domain.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<UserDto> findAllUsers();
    Optional<UserDto> findUserById(String id_user);
    void delete(String id_user);
    UserDto save(UserDto userDto);

}
