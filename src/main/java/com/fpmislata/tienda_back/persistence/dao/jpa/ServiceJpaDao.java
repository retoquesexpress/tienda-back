package com.fpmislata.tienda_back.persistence.dao.jpa;


import com.fpmislata.tienda_back.persistence.dao.jpa.entity.ServiceJpaEntity;

import java.util.List;
import java.util.Optional;

public interface ServiceJpaDao{
    List<ServiceJpaEntity> findAll();
    ServiceJpaEntity getById(String id_service);
    Optional<ServiceJpaEntity> findById(String id_service);
    ServiceJpaEntity update (ServiceJpaEntity serviceJpaEntity);
    ServiceJpaEntity create (ServiceJpaEntity serviceJpaEntity);
    void deleteById(String id_service);
}
