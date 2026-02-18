package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.domain.repository.UserRepository;
import com.fpmislata.tienda_back.domain.repository.entity.UserEntity;
import com.fpmislata.tienda_back.domain.service.UserService;
import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import com.fpmislata.tienda_back.exception.ResourceNotFoundException;
import com.fpmislata.tienda_back.mapper.UserMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAllUsers().stream().map(UserMapper.getInstance()::fromUserEntityToUserDto).toList();
    }

    @Override
    public Optional<UserDto> findUserById(Integer idUser) {
        Optional<UserEntity> user = userRepository.findUserById(idUser);
        if (user.isPresent()) {
            return user.map(UserMapper.getInstance()::fromUserEntityToUserDto);
        } else {
            throw new ResourceNotFoundException("User not found");
        }    }

    @Override
    public Optional<UserDto> findUserByUserName(String userName) {
        Optional<UserEntity> user = userRepository.findUserByUserName(userName);
        if (user.isPresent()) {
            return user.map(UserMapper.getInstance()::fromUserEntityToUserDto);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }
    @Transactional
    @Override
    public UserDto create(UserDto userDto) {
        Optional<UserEntity> user = userRepository.findUserById(userDto.idUser());
        if (user.isEmpty()) {
            return UserMapper.getInstance().fromUserEntityToUserDto(userRepository.save(user.get()));
        } else {
            throw new IllegalArgumentException("User already exists");
        }    }

    @Transactional
    @Override
    public UserDto update(UserDto userDto) {
        Optional<UserEntity> user = userRepository.findUserById(userDto.idUser());
        if (user.isPresent()) {
            return UserMapper.getInstance().fromUserEntityToUserDto(userRepository.save(user.get()));
        } else  {
            throw new ResourceNotFoundException("User does not exists");
        }
    }

    @Transactional
    @Override
    public void delete(Integer idUser) {
        Optional<UserEntity> user = userRepository.findUserById(idUser);
        if (user.isPresent()) {
            userRepository.delete(idUser);
        } else  {
            throw new ResourceNotFoundException("User does not exists");
        }
    }

    @Override
    public UserDto getById(Integer idUser) {
        Optional<UserEntity> user = userRepository.findUserById(idUser);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        return user.map(UserMapper.getInstance()::fromUserEntityToUserDto).get();
    }
}
