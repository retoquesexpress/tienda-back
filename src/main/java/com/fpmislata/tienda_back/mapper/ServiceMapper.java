package com.fpmislata.tienda_back.mapper;

import com.fpmislata.tienda_back.controller.webModel.request.ServiceInsertRequest;
import com.fpmislata.tienda_back.controller.webModel.request.ServiceUpdateRequest;
import com.fpmislata.tienda_back.controller.webModel.response.ServiceDetailResponse;
import com.fpmislata.tienda_back.domain.service.dto.ServiceEntity;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.ServiceJpaEntity;

public class ServiceMapper {
    private static ServiceMapper INSTANCE;

    private ServiceMapper() {
    }

    public static ServiceMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceMapper();
        }
        return INSTANCE;
    }

    public ServiceDetailResponse fromServiceDtoToServiceDetailResponse(ServiceEntity serviceDto) {
        if (serviceDto == null) {
            return null;
        }
        return new ServiceDetailResponse(
                serviceDto.idService(),
                serviceDto.name(),
                serviceDto.description(),
                serviceDto.price(),
                serviceDto.pictureUrl(),
                CategoryMapper.getInstance().fromCategoryDtoToCategoryDetailResponse(serviceDto.category()));
    }

    public ServiceEntity fromServiceInsertRequestToServiceDto(ServiceInsertRequest serviceInsertRequest) {
        if (serviceInsertRequest == null) {
            return null;
        }
        return new ServiceEntity(
                null,
                serviceInsertRequest.name(),
                serviceInsertRequest.description(),
                serviceInsertRequest.price(),
                serviceInsertRequest.pictureUrl(),
                CategoryMapper.getInstance().fromCategoryInsertRequestToCategoryDto(serviceInsertRequest.category()));
    }

    public ServiceEntity fromServiceUpdateRequestToServiceDto(ServiceUpdateRequest serviceUpdateRequest) {
        if (serviceUpdateRequest == null) {
            return null;
        }
        return new ServiceEntity(
                serviceUpdateRequest.idService(),
                serviceUpdateRequest.name(),
                serviceUpdateRequest.description(),
                serviceUpdateRequest.price(),
                serviceUpdateRequest.pictureUrl(),
                CategoryMapper.getInstance().fromCategoryUpdateRequestToCategoryDto(serviceUpdateRequest.category()));
    }

    public ServiceEntity fromServiceJpaEntityToServiceEntity(ServiceJpaEntity serviceJpaEntity) {
        if (serviceJpaEntity == null) {
            return null;
        }
        return new ServiceEntity(
                serviceJpaEntity.getIdService(),
                serviceJpaEntity.getName(),
                serviceJpaEntity.getDescription(),
                serviceJpaEntity.getPrice(),
                serviceJpaEntity.getPictureUrl(),
                CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryDto(serviceJpaEntity.getCategory()));

    }

    public ServiceJpaEntity fromServiceEntityToServiceJpaEntity(ServiceEntity serviceEntity) {
        if (serviceEntity == null) {
            return null;
        }
        return new ServiceJpaEntity(
                serviceEntity.idService(),
                serviceEntity.name(),
                serviceEntity.description(),
                serviceEntity.price(),
                serviceEntity.pictureUrl(),
                CategoryMapper.getInstance().fromCategoryDtoToCategoryJpaEntity(serviceEntity.category()));
    }
}
