package com.fpmislata.tienda_back.domain.repository;

import com.fpmislata.tienda_back.domain.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<UserDto> findAllUsers();
    Optional<UserDto> findUserById(String id_user);
    UserDto create(UserDto userDto);
    UserDto update(UserDto userDto);
    void delete(String id_user);
    UserDto save(UserDto userDto);
}
