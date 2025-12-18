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
    public ServiceJpaEntity getById(Integer idService) {
        return entityManager.find(ServiceJpaEntity.class, idService);
    }

    @Override
    public Optional<ServiceJpaEntity> findById(Integer idService) {
        return Optional.ofNullable(entityManager.find(ServiceJpaEntity.class, idService));
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
    public void deleteById(Integer idService) {
        ServiceJpaEntity serviceJpaEntity = entityManager.find(ServiceJpaEntity.class, idService);
        if (serviceJpaEntity != null) {
            entityManager.remove(serviceJpaEntity);
        }

    }
}
