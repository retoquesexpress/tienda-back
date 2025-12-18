package com.fpmislata.tienda_back.persistence.repository;

import com.fpmislata.tienda_back.domain.repository.ServiceRepository;
import com.fpmislata.tienda_back.domain.service.dto.ServiceEntity;
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
    public List<ServiceEntity> findAll() {
        return serviceJpaDao.findAll()
                .stream()
                .map(ServiceMapper.getInstance()::fromServiceJpaEntityToServiceEntity)
                .toList();
    }

    @Override
    public ServiceEntity getById(Integer id_service) {
        return serviceJpaDao.findById(id_service)
                .map(ServiceMapper.getInstance()::fromServiceJpaEntityToServiceEntity)
                .orElse(null);
    }

    @Override
    public Optional<ServiceEntity> findById(Integer id_service) {
        return serviceJpaDao.findById(id_service)
                .map(ServiceMapper.getInstance()::fromServiceJpaEntityToServiceEntity);
    }

    @Override
    public ServiceEntity update(ServiceEntity serviceEntity) {
        Integer serviceId = serviceEntity.idService();
        ServiceJpaEntity existingEntity = serviceJpaDao.findById(serviceId).orElseThrow(
                () -> new EntityNotFoundException("Servicio con ID " + serviceId + " no encontrado para actualizar."));
        existingEntity.setName(serviceEntity.name());
        existingEntity.setDescription(serviceEntity.description());
        existingEntity.setPrice(serviceEntity.price());
        existingEntity.setPictureUrl(serviceEntity.pictureUrl());
        ServiceJpaEntity updatedEntity = serviceJpaDao.update(existingEntity);
        return ServiceMapper.getInstance().fromServiceJpaEntityToServiceEntity(updatedEntity);
    }

    @Override
    public ServiceEntity create(ServiceEntity serviceEntity) {
        ServiceJpaEntity entity = ServiceMapper.getInstance().fromServiceEntityToServiceJpaEntity(serviceEntity);
        ServiceJpaEntity createdEntity = serviceJpaDao.create(entity);
        return ServiceMapper.getInstance().fromServiceJpaEntityToServiceEntity(createdEntity);
    }

    @Override
    public void deleteById(Integer id_service) {
        serviceJpaDao.deleteById(id_service);
    }
}
