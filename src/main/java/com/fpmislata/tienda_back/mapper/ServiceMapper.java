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


    public static ServiceDto fromServiceJpaEntityToServiceEntity(ServiceJpaEntity serviceJpaEntity) {
        if (serviceJpaEntity == null) {
            return null;
        }
        return new ServiceDto(
                serviceJpaEntity.getId_service(),
                serviceJpaEntity.getName(),
                serviceJpaEntity.getDescription(),
                serviceJpaEntity.getPrice(),
                serviceJpaEntity.getPictureUrl()
        );
    }

    public static ServiceJpaEntity fromServiceEntityToServiceJpaEntity(ServiceDto serviceDto) {
        if (serviceDto == null) {
            return null;
        }
        return new ServiceJpaEntity(
                serviceDto.id_service(),
                serviceDto.name(),
                serviceDto.description(),
                serviceDto.price(),
                serviceDto.pictureUrl()
        );
    }

    public static ServiceDetailResponse fromServiceDtoToServiceDetailResponse(ServiceDto serviceDto) {
        if (serviceDto == null) {
            return null;
        }
        return new ServiceDetailResponse(
                serviceDto.id_service(),
                serviceDto.name(),
                serviceDto.description(),
                serviceDto.price(),
                serviceDto.pictureUrl()
        );
    }

    public static ServiceDto fromServiceInsertRequestToServiceDto(ServiceInsertRequest serviceInsertRequest) {
        if (serviceInsertRequest == null) {
            return null;
        }
        return new ServiceDto(
                serviceInsertRequest.id_service(),
                serviceInsertRequest.name(),
                serviceInsertRequest.description(),
                serviceInsertRequest.price(),
                serviceInsertRequest.pictureUrl()
        );
    }

    public static ServiceDto fromServiceUpdateRequestToServiceDto(ServiceUpdateRequest serviceUpdateRequest) {
        if (serviceUpdateRequest == null) {
            return null;
        }
        return new ServiceDto(
                serviceUpdateRequest.id_service(),
                serviceUpdateRequest.name(),
                serviceUpdateRequest.description(),
                serviceUpdateRequest.price(),
                null
        );
    }
}
