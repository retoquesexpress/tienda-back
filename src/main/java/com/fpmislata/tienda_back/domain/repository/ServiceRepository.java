package com.fpmislata.tienda_back.domain.repository;

import com.fpmislata.tienda_back.domain.service.dto.ServiceEntity;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository {
    List<ServiceEntity> findAll();
    ServiceEntity getById(String id_service);
    Optional<ServiceEntity> findById(String id_service);
    ServiceEntity update (ServiceEntity serviceEntity);
    ServiceEntity create (ServiceEntity serviceEntity);
    void deleteById(String id_service);
}
