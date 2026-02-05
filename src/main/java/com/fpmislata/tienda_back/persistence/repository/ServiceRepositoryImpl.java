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
                .map(ServiceMapper.getInstance()::fromServiceJpaEntityToServiceDto)
                .toList();
    }

    @Override
    public ServiceDto getById(Integer id_service) {
        return serviceJpaDao.findById(id_service)
                .map(ServiceMapper.getInstance()::fromServiceJpaEntityToServiceDto)
                .orElse(null);
    }

    @Override
    public Optional<ServiceDto> findById(Integer id_service) {
        return serviceJpaDao.findById(id_service)
                .map(ServiceMapper.getInstance()::fromServiceJpaEntityToServiceDto);
    }

    @Override
    public ServiceDto update(ServiceDto serviceEntity) {
        Integer serviceId = serviceEntity.idService();
        ServiceJpaEntity existingEntity = serviceJpaDao.findById(serviceId).orElseThrow(
                () -> new EntityNotFoundException("Servicio con ID " + serviceId + " no encontrado para actualizar."));
        existingEntity.setName(serviceEntity.name());
        existingEntity.setDescription(serviceEntity.description());
        existingEntity.setPrice(serviceEntity.price());
        existingEntity.setPictureUrl(serviceEntity.pictureUrl());
        ServiceJpaEntity updatedEntity = serviceJpaDao.update(existingEntity);
        return ServiceMapper.getInstance().fromServiceJpaEntityToServiceDto(updatedEntity);
    }

    @Override
    public List<ServiceDto> findByCategory(Integer idCategory) {
        return serviceJpaDao.findByCategoryId(idCategory)
                .stream()
                .map(ServiceMapper.getInstance()::fromServiceJpaEntityToServiceDto)
                .toList();
    }

    @Override
    public ServiceDto create(ServiceDto serviceEntity) {
        ServiceJpaEntity entity = ServiceMapper.getInstance().fromServiceDtoToServiceJpaEntity(serviceEntity);
        ServiceJpaEntity createdEntity = serviceJpaDao.create(entity);
        return ServiceMapper.getInstance().fromServiceJpaEntityToServiceDto(createdEntity);
    }

    @Override
    public void deleteById(Integer id_service) {
        serviceJpaDao.deleteById(id_service);
    }
}
