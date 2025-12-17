package com.fpmislata.tienda_back.persistence.repository;

import com.fpmislata.tienda_back.domain.repository.AuthRepository;
import com.fpmislata.tienda_back.domain.repository.entity.UserEntity;
import com.fpmislata.tienda_back.mapper.UserMapper;
import com.fpmislata.tienda_back.persistence.dao.jpa.UserJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;
import jakarta.transaction.Transactional;
import java.util.Optional;

public class AuthRepositoryImpl implements AuthRepository {

    private final UserJpaDao userJpaDao;

    public AuthRepositoryImpl(UserJpaDao userJpaDao) {
        this.userJpaDao = userJpaDao;
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        Optional<UserJpaEntity> userJpaEntity = userJpaDao.findUserByUserName(username);
        return userJpaEntity.map(entity -> UserMapper.getInstance().fromUserJpaEntityToUserEntity(entity));
    }

    @Override
    public Optional<UserEntity> findById(String id) {
        Optional<UserJpaEntity> userJpaEntity = userJpaDao.findUserById(id);
        return userJpaEntity.map(entity -> UserMapper.getInstance().fromUserJpaEntityToUserEntity(entity));
    }

    @Override
    @Transactional
    public UserEntity register(UserEntity user) {
        UserJpaEntity userJpaEntity = new UserJpaEntity(
                user.id_user(),
                user.name(),
                user.email(),
                user.userName(),
                user.password(),
                user.phoneNumber(),
                user.address(),
                user.birthDate(),
                user.role()
        );
        UserJpaEntity savedEntity = userJpaDao.insert(userJpaEntity);
        return UserMapper.getInstance().fromUserJpaEntityToUserEntity(savedEntity);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userJpaDao.findUserByUserName(username).isPresent();
    }
}
