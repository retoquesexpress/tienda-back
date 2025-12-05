package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.domain.repository.UserRepository;
import com.fpmislata.tienda_back.domain.service.UserService;
import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import com.fpmislata.tienda_back.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository authorRepository) {
        this.userRepository = authorRepository;
    }


    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public Optional<UserDto> findUserById(String id_user) {
        Optional<UserDto> user = userRepository.findUserById(id_user);
        if (user.isPresent()) {
            return user;
        } else {
            throw new ResourceNotFoundException("Author not found");
        }    }

    @Override
    public UserDto create(UserDto userDto) {
        Optional<UserDto> user = userRepository.findUserById(userDto.id_user());
        if (user.isEmpty()) {
            return userRepository.save(userDto);
        } else {
            throw new IllegalArgumentException("Author already exists");
        }    }

    @Override
    public UserDto update(UserDto userDto) {
        Optional<UserDto> user = userRepository.findUserById(userDto.id_user());
        if (user.isPresent()) {
            return userRepository.save(userDto);
        } else  {
            throw new ResourceNotFoundException("Author does not exists");
        }
    }

    @Override
    public void delete(String id_user) {
        Optional<UserDto> user = userRepository.findUserById(id_user);
        if (user.isPresent()) {
            userRepository.delete(id_user);
        } else  {
            throw new ResourceNotFoundException("Author does not exists");
        }
    }
}
