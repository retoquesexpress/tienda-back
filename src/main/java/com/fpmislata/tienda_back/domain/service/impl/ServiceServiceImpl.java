package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.domain.repository.ServiceRepository;
import com.fpmislata.tienda_back.domain.service.ServiceService;
import com.fpmislata.tienda_back.domain.service.dto.ServiceDto;
import com.fpmislata.tienda_back.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public class ServiceServiceImpl implements ServiceService {
private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<ServiceDto> findAll() {
       if (serviceRepository.findAll().isEmpty()) {
           throw new ResourceNotFoundException("No services found");
       }
        return serviceRepository.findAll();
    }

    @Override
    public ServiceDto getById(String id_service) {
        Optional<ServiceDto> service = serviceRepository.findById(id_service);
        if (service.isEmpty()) {
            throw new ResourceNotFoundException("Service not found");
        }
        return service.get();
    }

    @Override
    public Optional<ServiceDto> findById(String id_service) {
        Optional<ServiceDto> service = serviceRepository.findById(id_service);
        if (service.isEmpty()) {
            throw new ResourceNotFoundException("Service not found");
        }
        return service;
    }

    @Override
    public ServiceDto update(ServiceDto serviceDto) {
        Optional<ServiceDto> service = serviceRepository.findById(serviceDto.id_service());
        if (service.isEmpty()) {
            throw new ResourceNotFoundException("Service not found");
        }
        return serviceRepository.update(serviceDto);
    }

    @Override
    public ServiceDto create(ServiceDto serviceDto) {
     Optional<ServiceDto> service = serviceRepository.findById(serviceDto.id_service());
        if (service.isPresent()) {
            throw new IllegalArgumentException("Service already exists");
        }
        return serviceRepository.create(serviceDto);
    }

    @Override
    public void deleteById(String id_service) {
        Optional<ServiceDto> service = serviceRepository.findById(id_service);
        if (service.isEmpty()) {
            throw new ResourceNotFoundException("Service not found");
        }
        serviceRepository.deleteById(id_service);
    }
}
