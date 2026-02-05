package com.fpmislata.tienda_back.mapper;

import com.fpmislata.tienda_back.controller.webModel.request.ServiceInsertRequest;
import com.fpmislata.tienda_back.controller.webModel.request.ServiceUpdateRequest;
import com.fpmislata.tienda_back.controller.webModel.response.ServiceDetailResponse;
import com.fpmislata.tienda_back.domain.service.dto.ServiceDto;
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

    public ServiceDetailResponse fromServiceDtoToServiceDetailResponse(ServiceDto serviceDto) {
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

    public ServiceDto fromServiceInsertRequestToServiceDto(ServiceInsertRequest serviceInsertRequest) {
        if (serviceInsertRequest == null) {
            return null;
        }
        return new ServiceDto(
                null,
                serviceInsertRequest.name(),
                serviceInsertRequest.description(),
                serviceInsertRequest.price(),
                serviceInsertRequest.pictureUrl(),
                CategoryMapper.getInstance().fromCategoryInsertRequestToCategoryDto(serviceInsertRequest.category()));
    }

    public ServiceDto fromServiceUpdateRequestToServiceDto(ServiceUpdateRequest serviceUpdateRequest) {
        if (serviceUpdateRequest == null) {
            return null;
        }
        return new ServiceDto(
                Integer.valueOf(serviceUpdateRequest.idService()),
                serviceUpdateRequest.name(),
                serviceUpdateRequest.description(),
                serviceUpdateRequest.price(),
                serviceUpdateRequest.pictureUrl(),
                CategoryMapper.getInstance().fromCategoryUpdateRequestToCategoryDto(serviceUpdateRequest.category()));
    }

    public ServiceDto fromServiceJpaEntityToServiceDto(ServiceJpaEntity serviceJpaEntity) {
        if (serviceJpaEntity == null) {
            return null;
        }
        return new ServiceDto(
                serviceJpaEntity.getIdService(),
                serviceJpaEntity.getName(),
                serviceJpaEntity.getDescription(),
                serviceJpaEntity.getPrice(),
                serviceJpaEntity.getPictureUrl(),
                CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryDto(serviceJpaEntity.getCategory()));
    }

    public ServiceJpaEntity fromServiceDtoToServiceJpaEntity(ServiceDto serviceDto) {
        if (serviceDto == null) {
            return null;
        }
        return new ServiceJpaEntity(
                serviceDto.idService(),
                serviceDto.name(),
                serviceDto.description(),
                serviceDto.price(),
                serviceDto.pictureUrl(),
                CategoryMapper.getInstance().fromCategoryDtoToCategoryJpaEntity(serviceDto.category()));
    }

    public com.fpmislata.tienda_back.domain.model.Service fromServiceDtoToService(ServiceDto serviceDto) {
        if (serviceDto == null) {
            return null;
        }
        return new com.fpmislata.tienda_back.domain.model.Service(
                serviceDto.idService(),
                serviceDto.name(),
                serviceDto.description(),
                serviceDto.price(),
                serviceDto.pictureUrl(),
                CategoryMapper.getInstance().fromCategoryDtoToCategory(serviceDto.category()));
    }

    public ServiceDto fromServiceToServiceDto(com.fpmislata.tienda_back.domain.model.Service service) {
        if (service == null) {
            return null;
        }
        return new ServiceDto(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.getPrice(),
                service.getPictureUrl(),
                CategoryMapper.getInstance().fromCategoryToCategoryDto(service.getCategory()));
    }

    public com.fpmislata.tienda_back.domain.repository.entity.ServiceEntity fromServiceJpaEntityToServiceEntity(
            ServiceJpaEntity serviceJpaEntity) {
        if (serviceJpaEntity == null) {
            return null;
        }
        return new com.fpmislata.tienda_back.domain.repository.entity.ServiceEntity(
                String.valueOf(serviceJpaEntity.getIdService()),
                serviceJpaEntity.getName(),
                serviceJpaEntity.getDescription(),
                serviceJpaEntity.getPrice(),
                serviceJpaEntity.getPictureUrl(),
                CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryEntity(serviceJpaEntity.getCategory()));
    }

    public ServiceJpaEntity fromServiceEntityToServiceJpaEntity(
            com.fpmislata.tienda_back.domain.repository.entity.ServiceEntity serviceEntity) {
        if (serviceEntity == null) {
            return null;
        }
        return new ServiceJpaEntity(
                Integer.valueOf(serviceEntity.idService()),
                serviceEntity.name(),
                serviceEntity.description(),
                serviceEntity.price(),
                serviceEntity.pictureUrl(),
                CategoryMapper.getInstance().fromCategoryEntityToCategoryJpaEntity(serviceEntity.category()));
    }

    public com.fpmislata.tienda_back.domain.model.Service fromServiceJpaEntityToService(
            ServiceJpaEntity serviceJpaEntity) {
        if (serviceJpaEntity == null) {
            return null;
        }
        return new com.fpmislata.tienda_back.domain.model.Service(
                serviceJpaEntity.getIdService(),
                serviceJpaEntity.getName(),
                serviceJpaEntity.getDescription(),
                serviceJpaEntity.getPrice(),
                serviceJpaEntity.getPictureUrl(),
                CategoryMapper.getInstance().fromCategoryJpaEntityToCategory(serviceJpaEntity.getCategory()));
    }

    public ServiceJpaEntity fromServiceToServiceJpaEntity(com.fpmislata.tienda_back.domain.model.Service service) {
        if (service == null) {
            return null;
        }
        return new ServiceJpaEntity(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.getPrice(),
                service.getPictureUrl(),
                CategoryMapper.getInstance().fromCategoryToCategoryJpaEntity(service.getCategory()));
    }

    public ServiceDto fromServiceEntityToServiceDto(
            com.fpmislata.tienda_back.domain.repository.entity.ServiceEntity serviceEntity) {
        if (serviceEntity == null) {
            return null;
        }
        return new ServiceDto(
                Integer.valueOf(serviceEntity.idService()),
                serviceEntity.name(),
                serviceEntity.description(),
                serviceEntity.price(),
                serviceEntity.pictureUrl(),
                CategoryMapper.getInstance().fromCategoryEntityToCategoryDto(serviceEntity.category()));
    }
}
