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
        return entityManager.createQuery("SELECT u FROM UserJpaEntity u ORDER BY u.idUser", UserJpaEntity.class)
                .getResultList();
    }

    @Override
    public Optional<UserJpaEntity> findUserById(String idUser) {
        UserJpaEntity userJpaEntity = entityManager.find(UserJpaEntity.class, idUser);
        return Optional.ofNullable(userJpaEntity);
    }

    @Override
    public UserJpaEntity update(UserJpaEntity userDto) {
        entityManager.flush();
        entityManager.merge(userDto);
        return userDto;
    }

    @Override
    public void delete(String idUser) {
        UserJpaEntity userJpaEntity = entityManager.find(UserJpaEntity.class, idUser);
        entityManager.createQuery("DELETE FROM UserJpaEntity u WHERE user.idUser = :idUser")
                .setParameter("idUser", idUser)
                .executeUpdate();
        entityManager.remove(userJpaEntity);
    }

    @Override
    public UserJpaEntity insert(UserJpaEntity userDto) {
        entityManager.persist(userDto);
        return userDto;
    }

    @Override
    public UserJpaEntity getById(String idUser) {
        return entityManager.find(UserJpaEntity.class, idUser);
    }

    @Override
    public Optional<UserJpaEntity> findUserByUserName(String userName) {
        List<UserJpaEntity> result = entityManager
                .createQuery("SELECT u FROM UserJpaEntity u WHERE u.userName = :userName", UserJpaEntity.class)
                .setParameter("userName", userName)
                .getResultList();
        if (result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(result.get(0));
        }
    }
}
