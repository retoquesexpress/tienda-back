package com.fpmislata.tienda_back.persistence.repository;

import com.fpmislata.tienda_back.domain.repository.ServiceRepository;
import com.fpmislata.tienda_back.domain.service.dto.ServiceDto;
import com.fpmislata.tienda_back.mapper.ServiceMapper;
import com.fpmislata.tienda_back.persistence.dao.jpa.ServiceJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.ServiceJpaEntity;

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
        ServiceJpaEntity entity = ServiceMapper.fromServiceEntityToServiceJpaEntity(serviceDto);
        return ServiceMapper.fromServiceJpaEntityToServiceEntity(
                serviceJpaDao.update(entity)
        );    }

    @Override
    public ServiceDto create(ServiceDto serviceDto) {
        ServiceJpaEntity entity = ServiceMapper.fromServiceEntityToServiceJpaEntity(serviceDto);
        return ServiceMapper.fromServiceJpaEntityToServiceEntity(
                serviceJpaDao.create(entity)
        );
    }

    @Override
    public void deleteById(String id_service) {
        serviceJpaDao.deleteById(id_service);

    }
}
