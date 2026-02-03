package com.fpmislata.tienda_back.domain.repository;

import com.fpmislata.tienda_back.domain.service.dto.ServiceDto;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository {
    List<ServiceDto> findAll();

    ServiceDto getById(Integer idService);

    Optional<ServiceDto> findById(Integer idService);

    ServiceDto update(ServiceDto serviceEntity);

    List<ServiceDto> findByCategory(Integer idCategory);

    ServiceDto create(ServiceDto serviceEntity);

    void deleteById(Integer idService);
}
