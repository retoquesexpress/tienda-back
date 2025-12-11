package com.fpmislata.tienda_back.controller;

import com.fpmislata.tienda_back.controller.webModel.request.ServiceInsertRequest;
import com.fpmislata.tienda_back.controller.webModel.response.ServiceDetailResponse;
import com.fpmislata.tienda_back.domain.service.ServiceService;
import com.fpmislata.tienda_back.domain.service.dto.ServiceDto;
import com.fpmislata.tienda_back.mapper.ServiceMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/services")
public class ServiceController{
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/{id_service}")
    public ResponseEntity<ServiceDetailResponse> getById(@PathVariable String id_service) {
        ServiceDto serviceDto = serviceService.getById(id_service);
        ServiceDetailResponse serviceDetailResponse = ServiceMapper.fromServiceDtoToServiceDetailResponse(serviceDto);
        return new ResponseEntity<>(serviceDetailResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ServiceDetailResponse> create(@RequestBody ServiceInsertRequest serviceInsertRequest) {
        ServiceDto serviceDto = new ServiceDto(
                serviceInsertRequest.id_service(),
                serviceInsertRequest.name(),
                serviceInsertRequest.description(),
                serviceInsertRequest.price(),
                serviceInsertRequest.pictureUrl()
        );

        ServiceDto createdService = serviceService.create(serviceDto);
        ServiceDetailResponse createdServiceResponse =
                ServiceMapper.fromServiceDtoToServiceDetailResponse(createdService);

        return new ResponseEntity<>(createdServiceResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id_service}")
    public ResponseEntity<ServiceDetailResponse> update(@PathVariable String id_service,
                                                        @RequestBody ServiceInsertRequest serviceInsertRequest) {

        ServiceDto serviceDto = new ServiceDto(
                id_service,
                serviceInsertRequest.name(),
                serviceInsertRequest.description(),
                serviceInsertRequest.price(),
                serviceInsertRequest.pictureUrl()
        );

        ServiceDto updatedService = serviceService.update(serviceDto);
        ServiceDetailResponse updatedServiceResponse =
                ServiceMapper.fromServiceDtoToServiceDetailResponse(updatedService);

        return new ResponseEntity<>(updatedServiceResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id_service}")
    public ResponseEntity<Void> delete(@PathVariable String id_service) {
        serviceService.deleteById(id_service);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}