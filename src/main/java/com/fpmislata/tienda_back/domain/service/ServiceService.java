package com.fpmislata.tienda_back.domain.service;

import com.fpmislata.tienda_back.domain.service.dto.ServiceDto;

import java.util.List;
import java.util.Optional;

public interface ServiceService {
    List<ServiceDto> findAll();

    ServiceDto getById(Integer idService);

    Optional<ServiceDto> findById(Integer idService);

    List<ServiceDto> findByCategory(Integer idCategory);

    ServiceDto update(ServiceDto serviceEntity);

    ServiceDto create(ServiceDto serviceEntity);

    void deleteById(Integer idService);
}
