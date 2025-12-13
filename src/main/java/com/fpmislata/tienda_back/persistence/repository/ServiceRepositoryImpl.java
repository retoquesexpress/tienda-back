package com.fpmislata.tienda_back.persistence.repository;

import com.fpmislata.tienda_back.domain.repository.ServiceRepository;
import com.fpmislata.tienda_back.domain.service.dto.ServiceDto;
import com.fpmislata.tienda_back.mapper.ServiceMapper;
import com.fpmislata.tienda_back.persistence.dao.jpa.ServiceJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.ServiceJpaEntity;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class ServiceRepositoryImpl implements ServiceRepository {

    private final ServiceJpaDao serviceJpaDao;

    public ServiceRepositoryImpl(ServiceJpaDao serviceJpaDao) {
        this.serviceJpaDao = serviceJpaDao;
    }

    @Override
    public List<ServiceDto> findAll() {
        return serviceJpaDao.findAll()
                .stream()
                .map(ServiceMapper::fromServiceJpaEntityToServiceEntity)
                .toList();
    }

    @Override
    public ServiceDto getById(String id_service) {
        return serviceJpaDao.findById(id_service)
                .map(ServiceMapper::fromServiceJpaEntityToServiceEntity)
                .orElse(null);
    }

    @Override
    public Optional<ServiceDto> findById(String id_service) {
        return serviceJpaDao.findById(id_service)
                .map(ServiceMapper::fromServiceJpaEntityToServiceEntity);
    }

    @Override
    public ServiceDto update(ServiceDto serviceDto) {
        String serviceId = serviceDto.id_service();
        ServiceJpaEntity existingEntity = serviceJpaDao.findById(serviceId).orElseThrow(() -> new EntityNotFoundException("Servicio con ID " + serviceId + " no encontrado para actualizar."));
        existingEntity.setName(serviceDto.name());
        existingEntity.setDescription(serviceDto.description());
        existingEntity.setPrice(serviceDto.price());
        existingEntity.setPictureUrl(serviceDto.pictureUrl());
        ServiceJpaEntity updatedEntity = serviceJpaDao.update(existingEntity);
        return ServiceMapper.fromServiceJpaEntityToServiceEntity(updatedEntity);
    }

    @Override
    public ServiceDto create(ServiceDto serviceDto) {
        ServiceJpaEntity entity = ServiceMapper.fromServiceEntityToServiceJpaEntity(serviceDto);
        ServiceJpaEntity createdEntity = serviceJpaDao.create(entity);
        return ServiceMapper.fromServiceJpaEntityToServiceEntity(createdEntity);
    }

    @Override
    public void deleteById(String id_service) {
        serviceJpaDao.deleteById(id_service);

    }
}
