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
    public List<UserEntity> findAllUsers() {
        return userJpaDao.findAllUsers().stream().map(UserMapper.getInstance()::fromUserJpaEntityToUserEntity).toList();
    }

    public Optional<UserEntity> findUserById(Integer idUser) {
        return userJpaDao.findUserById(idUser).map(UserMapper.getInstance()::fromUserJpaEntityToUserEntity);
    }

    public void delete(Integer idUser) {
        userJpaDao.delete(idUser);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        if (userEntity.idUser() == null) {
            return UserMapper.getInstance().fromUserJpaEntityToUserEntity(
                    userJpaDao.insert(UserMapper.getInstance().fromUserEntityToUserJpaEntity(userEntity)));
        } else {
            return UserMapper.getInstance().fromUserJpaEntityToUserEntity(
                    userJpaDao.update(UserMapper.getInstance().fromUserEntityToUserJpaEntity(userEntity)));
        }

    }

    public UserEntity getById(Integer idUser) {
        return userJpaDao.findUserById(idUser)
                .map(UserMapper.getInstance()::fromUserJpaEntityToUserEntity)
                .orElse(null);
    }

    @Override
    public Optional<UserEntity> findUserByUserName(String userName) {
        return userJpaDao.findUserByUserName(userName).map(UserMapper.getInstance()::fromUserJpaEntityToUserEntity);
    }
}
