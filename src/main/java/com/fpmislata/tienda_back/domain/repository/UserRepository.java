package com.fpmislata.tienda_back.domain.repository;

import com.fpmislata.tienda_back.domain.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<UserDto> findAllUsers();
    Optional<UserDto> findUserById(Integer idUser);
    void delete(Integer idUser);
    UserDto save(UserDto userDto);
    UserDto getById(Integer idUser);
    Optional<UserDto> findUserByUserName(String userName);
}
