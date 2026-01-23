package com.fpmislata.tienda_back.domain.service;

import com.fpmislata.tienda_back.domain.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> findAllUsers();
    Optional<UserDto> findUserById(Integer idUser);
    Optional<UserDto> findUserByUserName(String userName);
    UserDto create(UserDto userDto);
    UserDto update(UserDto userDto);
    void delete(Integer idUser);
    UserDto getById(Integer idUser);
}
