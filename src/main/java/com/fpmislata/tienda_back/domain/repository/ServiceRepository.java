package com.fpmislata.tienda_back.domain.repository;

import com.fpmislata.tienda_back.domain.service.dto.ServiceEntity;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository {
    List<ServiceEntity> findAll();

    ServiceEntity getById(Integer idService);

    Optional<ServiceEntity> findById(Integer idService);

    ServiceEntity update(ServiceEntity serviceEntity);

    ServiceEntity create(ServiceEntity serviceEntity);

    void deleteById(Integer idService);
}
