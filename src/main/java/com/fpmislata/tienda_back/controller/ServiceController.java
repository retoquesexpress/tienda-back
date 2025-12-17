package com.fpmislata.tienda_back.controller;

import com.fpmislata.tienda_back.controller.webModel.request.ServiceInsertRequest;
import com.fpmislata.tienda_back.controller.webModel.request.ServiceUpdateRequest;
import com.fpmislata.tienda_back.controller.webModel.response.ServiceDetailResponse;
import com.fpmislata.tienda_back.domain.service.ServiceService;
import com.fpmislata.tienda_back.domain.service.dto.ServiceEntity;
import com.fpmislata.tienda_back.mapper.ServiceMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public ResponseEntity<ServiceDetailResponse> findAll() {
        List<ServiceDetailResponse> serviceDetailResponse = serviceService.findAll()
                .stream()
                .map(ServiceMapper.getInstance()::fromServiceDtoToServiceDetailResponse)
                .toList();
        return new ResponseEntity(serviceDetailResponse, HttpStatus.OK);
    }

    @GetMapping("/{id_service}")
    public ResponseEntity<ServiceDetailResponse> getById(@PathVariable Integer id_service) {
        ServiceDetailResponse serviceDetailResponse = ServiceMapper.getInstance()
                .fromServiceDtoToServiceDetailResponse(serviceService.getById(id_service));
        return new ResponseEntity<>(serviceDetailResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ServiceDetailResponse> create(@RequestBody ServiceInsertRequest serviceInsertRequest) {
        ServiceEntity serviceDto = ServiceMapper.getInstance()
                .fromServiceInsertRequestToServiceDto(serviceInsertRequest);
        ServiceEntity createdService = serviceService.create(serviceDto);
        return new ResponseEntity<>(ServiceMapper.getInstance().fromServiceDtoToServiceDetailResponse(createdService),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id_service}")
    public ResponseEntity<ServiceDetailResponse> update(@PathVariable Integer id_service,
            @RequestBody ServiceUpdateRequest serviceUpdateRequest) {
        if (!id_service.equals(serviceUpdateRequest.id_service())) {
            throw new IllegalArgumentException("ID in path and request body must match");
        }
        ServiceEntity serviceDto = ServiceMapper.getInstance()
                .fromServiceUpdateRequestToServiceDto(serviceUpdateRequest);
        ServiceEntity updatedService = serviceService.update(serviceDto);
        return new ResponseEntity<>(ServiceMapper.getInstance().fromServiceDtoToServiceDetailResponse(updatedService),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id_service}")
    public ResponseEntity<Void> delete(@PathVariable Integer id_service) {
        serviceService.deleteById(id_service);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}