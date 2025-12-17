package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.domain.repository.ServiceRepository;
import com.fpmislata.tienda_back.domain.service.ServiceService;
import com.fpmislata.tienda_back.domain.service.dto.ServiceEntity;
import com.fpmislata.tienda_back.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<ServiceEntity> findAll() {
        if (serviceRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("No services found");
        }
        return serviceRepository.findAll();
    }

    @Override
    public ServiceEntity getById(Integer id_service) {
        Optional<ServiceEntity> service = serviceRepository.findById(id_service);
        if (service.isEmpty()) {
            throw new ResourceNotFoundException("Service not found");
        }
        return service.get();
    }

    @Override
    public Optional<ServiceEntity> findById(Integer id_service) {
        Optional<ServiceEntity> service = serviceRepository.findById(id_service);
        if (service.isEmpty()) {
            throw new ResourceNotFoundException("Service not found");
        }
        return service;
    }

    @Transactional
    @Override
    public ServiceEntity update(ServiceEntity serviceDto) {
        Optional<ServiceEntity> service = serviceRepository.findById(serviceDto.id_service());
        if (service.isEmpty()) {
            throw new ResourceNotFoundException("Service not found");
        }
        return serviceRepository.update(serviceDto);
    }

    @Transactional
    @Override
    public ServiceEntity create(ServiceEntity serviceDto) {
        return serviceRepository.create(serviceDto);
    }

    @Transactional
    @Override
    public void deleteById(Integer id_service) {
        Optional<ServiceEntity> service = serviceRepository.findById(id_service);
        if (service.isEmpty()) {
            throw new ResourceNotFoundException("Service not found");
        }
        serviceRepository.deleteById(id_service);
    }
}
