package com.fpmislata.tienda_back.domain.repository;

import com.fpmislata.tienda_back.domain.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<UserDto> findAllUsers();
    Optional<UserDto> findUserById(String idUser);
    void delete(String idUser);
    UserDto save(UserDto userDto);
    UserDto getById(String idUser);
    Optional<UserDto> findUserByUserName(String userName);
}
