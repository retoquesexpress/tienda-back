package com.fpmislata.tienda_back.domain.service;

import com.fpmislata.tienda_back.domain.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> findAllUsers();
    Optional<UserDto> findUserById(String idUser);
    Optional<UserDto> findUserByUserName(String userName);
    UserDto create(UserDto userDto);
    UserDto update(UserDto userDto);
    void delete(String idUser);
    UserDto getById(String idUser);
}
