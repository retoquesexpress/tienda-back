package com.fpmislata.tienda_back.persistence.dao.jpa.impl;

import com.fpmislata.tienda_back.persistence.dao.jpa.ServiceJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.ServiceJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class ServiceJpaDaoImpl implements ServiceJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ServiceJpaEntity> findAll() {
        return entityManager.createQuery("SELECT s FROM ServiceJpaEntity s", ServiceJpaEntity.class)
                .getResultList();
    }

    @Override
    public ServiceJpaEntity getById(Integer id_service) {
        return entityManager.find(ServiceJpaEntity.class, id_service);
    }

    @Override
    public Optional<ServiceJpaEntity> findById(Integer id_service) {
        return Optional.ofNullable(entityManager.find(ServiceJpaEntity.class, id_service));
    }

    @Override
    public ServiceJpaEntity update(ServiceJpaEntity serviceJpaEntity) {
        return entityManager.merge(serviceJpaEntity);
    }

    @Override
    public ServiceJpaEntity create(ServiceJpaEntity serviceJpaEntity) {
        return entityManager.merge(serviceJpaEntity);
    }

    @Override
    public void deleteById(Integer id_service) {
        ServiceJpaEntity serviceJpaEntity = entityManager.find(ServiceJpaEntity.class, id_service);
        if (serviceJpaEntity != null) {
            entityManager.remove(serviceJpaEntity);
        }

    }
}
