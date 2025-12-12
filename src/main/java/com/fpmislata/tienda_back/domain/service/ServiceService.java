package com.fpmislata.tienda_back.domain.service;

import com.fpmislata.tienda_back.domain.service.dto.ServiceDto;

import java.util.List;
import java.util.Optional;

public interface ServiceService {
    List<ServiceDto> findAll();
    ServiceDto getById(String id_service);
    Optional<ServiceDto> findById(String id_service);
    ServiceDto update (ServiceDto serviceDto);
    ServiceDto create (ServiceDto serviceDto);
    void deleteById(String id_service);
}
