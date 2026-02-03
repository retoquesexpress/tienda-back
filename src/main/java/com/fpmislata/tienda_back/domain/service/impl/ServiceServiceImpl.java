package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.domain.repository.ServiceRepository;
import com.fpmislata.tienda_back.domain.service.ServiceService;
import com.fpmislata.tienda_back.domain.service.dto.ServiceDto;
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
    public List<ServiceDto> findAll() {
        if (serviceRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("No services found");
        }
        return serviceRepository.findAll();
    }

    @Override
    public ServiceDto getById(Integer idService) {
        Optional<ServiceDto> service = serviceRepository.findById(idService);
        if (service.isEmpty()) {
            throw new ResourceNotFoundException("Service not found");
        }
        return service.get();
    }

    @Override
    public Optional<ServiceDto> findById(Integer idService) {
        Optional<ServiceDto> service = serviceRepository.findById(idService);
        if (service.isEmpty()) {
            throw new ResourceNotFoundException("Service not found");
        }
        return service;
    }

    @Override
    public List<ServiceDto> findByCategory(Integer idCategory) {
        return serviceRepository.findByCategory(idCategory);
    }

    @Transactional
    @Override
    public ServiceDto update(ServiceDto serviceDto) {
        Optional<ServiceDto> service = serviceRepository.findById(serviceDto.idService());
        if (service.isEmpty()) {
            throw new ResourceNotFoundException("Service not found");
        }
        return serviceRepository.update(serviceDto);
    }

    @Transactional
    @Override
    public ServiceDto create(ServiceDto serviceDto) {
        return serviceRepository.create(serviceDto);
    }

    @Transactional
    @Override
    public void deleteById(Integer idService) {
        Optional<ServiceDto> service = serviceRepository.findById(idService);
        if (service.isEmpty()) {
            throw new ResourceNotFoundException("Service not found");
        }
        serviceRepository.deleteById(idService);
    }
}
