package com.fpmislata.tienda_back.domain.repository;

import com.fpmislata.tienda_back.domain.service.dto.ServiceDto;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository {
    List<ServiceDto> findAll();
    ServiceDto getById(String id_service);
    Optional<ServiceDto> findById(String id_service);
    ServiceDto update (ServiceDto serviceDto);
    ServiceDto create (ServiceDto serviceDto);
    void deleteById(String id_service);
}
