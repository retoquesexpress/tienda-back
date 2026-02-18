package com.fpmislata.tienda_back.domain.repository;

import com.fpmislata.tienda_back.domain.repository.entity.ServiceEntity;
import com.fpmislata.tienda_back.domain.service.dto.ServiceDto;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository {
    List<ServiceEntity> findAll();

    ServiceEntity getById(Integer idService);

    Optional<ServiceEntity> findById(Integer idService);

    ServiceEntity update(ServiceEntity serviceEntity);

    List<ServiceEntity> findByCategory(Integer idCategory);

    ServiceEntity create(ServiceEntity serviceEntity);

    void deleteById(Integer idService);
}
