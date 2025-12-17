package com.fpmislata.tienda_back.persistence.dao.jpa.impl;

import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import com.fpmislata.tienda_back.persistence.dao.jpa.UserJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class UserJpaDaoImpl implements UserJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserJpaEntity> findAllUsers() {
        return entityManager.createQuery("SELECT u FROM UserJpaEntity u ORDER BY u.id_user", UserJpaEntity.class)
                .getResultList();
    }

    @Override
    public Optional<UserJpaEntity> findUserById(String id_user) {
        UserJpaEntity userJpaEntity = entityManager.find(UserJpaEntity.class, id_user);
        return Optional.ofNullable(userJpaEntity);
    }

    @Override
    public UserJpaEntity update(UserJpaEntity userDto) {
        entityManager.flush();
        entityManager.merge(userDto);
        return userDto;
    }

    @Override
    public void delete(String id_user) {
        UserJpaEntity userJpaEntity = entityManager.find(UserJpaEntity.class, id_user);
        entityManager.createQuery("DELETE FROM UserJpaEntity u WHERE user.id_user = :id_user")
                .setParameter("id_user", id_user)
                .executeUpdate();
        entityManager.remove(userJpaEntity);
    }

    @Override
    public UserJpaEntity insert(UserJpaEntity userDto) {
        entityManager.persist(userDto);
        return userDto;
    }

    @Override
    public UserJpaEntity getById(String id_user) {
        return entityManager.find(UserJpaEntity.class, id_user);
    }

    @Override
    public Optional<UserJpaEntity> findUserByUserName(String userName) {
        List<UserJpaEntity> result = entityManager.createQuery("SELECT u FROM UserJpaEntity u WHERE u.userName = :userName", UserJpaEntity.class)
                .setParameter("userName", userName)
                .getResultList();
        if (result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(result.get(0));
        }
    }
}
