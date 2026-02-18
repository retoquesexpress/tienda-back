package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.domain.repository.ServiceRepository;
import com.fpmislata.tienda_back.domain.repository.entity.ServiceEntity;
import com.fpmislata.tienda_back.domain.service.ServiceService;
import com.fpmislata.tienda_back.domain.service.dto.ServiceDto;
import com.fpmislata.tienda_back.exception.ResourceNotFoundException;
import com.fpmislata.tienda_back.mapper.ServiceMapper;
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
        return serviceRepository.findAll().stream().map(ServiceMapper.getInstance()::fromServiceEntityToServiceDto).toList();
    }

    @Override
    public ServiceDto getById(Integer idService) {
        Optional<ServiceEntity> service = serviceRepository.findById(idService);
        if (service.isEmpty()) {
            throw new ResourceNotFoundException("Service not found");
        }
        return service.map(ServiceMapper.getInstance()::fromServiceEntityToServiceDto).get();
    }

    @Override
    public Optional<ServiceDto> findById(Integer idService) {
        Optional<ServiceEntity> service = serviceRepository.findById(idService);
        if (service.isEmpty()) {
            throw new ResourceNotFoundException("Service not found");
        }
        return service.map(ServiceMapper.getInstance()::fromServiceEntityToServiceDto);
    }

    @Override
    public List<ServiceDto> findByCategory(Integer idCategory) {
        return serviceRepository.findByCategory(idCategory)
                .stream()
                .map(ServiceMapper.getInstance()::fromServiceEntityToServiceDto)
                .toList();
    }

    @Transactional
    @Override
    public ServiceDto update(ServiceDto serviceDto) {
        Optional<ServiceEntity> service = serviceRepository.findById(serviceDto.idService());
        if (service.isEmpty()) {
            throw new ResourceNotFoundException("Service not found");
        }
        return ServiceMapper.getInstance().fromServiceEntityToServiceDto(serviceRepository.update(service.get()));
    }

    @Transactional
    @Override
    public ServiceDto create(ServiceDto serviceDto) {
        return ServiceMapper.getInstance().fromServiceEntityToServiceDto(serviceRepository.create(ServiceMapper.getInstance().fromServiceDtoToServiceEntity(serviceDto)));
    }

    @Transactional
    @Override
    public void deleteById(Integer idService) {
        Optional<ServiceEntity> service = serviceRepository.findById(idService);
        if (service.isEmpty()) {
            throw new ResourceNotFoundException("Service not found");
        }
        serviceRepository.deleteById(idService);
    }
}
