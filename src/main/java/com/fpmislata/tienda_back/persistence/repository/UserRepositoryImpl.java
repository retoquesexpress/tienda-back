package com.fpmislata.tienda_back.persistence.repository;

import com.fpmislata.tienda_back.domain.repository.UserRepository;
import com.fpmislata.tienda_back.domain.repository.entity.UserEntity;
import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import com.fpmislata.tienda_back.mapper.UserMapper;
import com.fpmislata.tienda_back.persistence.dao.jpa.UserJpaDao;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private UserJpaDao userJpaDao;
    public UserRepositoryImpl(UserJpaDao userJpaDao) {
        this.userJpaDao = userJpaDao;
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userJpaDao.findAllUsers().stream().map(UserMapper.getInstance()::fromUserJpaEntityToUserDto).toList();
    }

    @Override
    public Optional<UserDto> findUserById(String id_user) {
        return userJpaDao.findUserById(id_user).map(UserMapper.getInstance()::fromUserJpaEntityToUserDto);
    }


    @Override
    public void delete(String id_user) {
        userJpaDao.delete(id_user);
    }

    @Override
    public UserDto save(UserDto userDto) {
        if (userDto.id_user() == null) {
            return UserMapper.getInstance().fromUserJpaEntityToUserDto(
                    userJpaDao.insert(UserMapper.getInstance().fromUserDtoToUserJpaEntity(userDto)));
        } else {
            return UserMapper.getInstance().fromUserJpaEntityToUserDto(
                    userJpaDao.update(UserMapper.getInstance().fromUserDtoToUserJpaEntity(userDto)));
        }

    }

    @Override
    public UserDto getById(String id_user) {
        return userJpaDao.findUserById(id_user)
                .map(UserMapper.getInstance()::fromUserJpaEntityToUserDto)
                .orElse(null);
    }
}
