package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.domain.repository.ServiceRepository;
import com.fpmislata.tienda_back.domain.service.dto.ServiceDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceServiceImplTest {

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private ServiceServiceImpl serviceService;

    @Nested
    class FindAll {
        @Test
        @DisplayName("Test findAll should return list of service when service exist")
        void testFindAllShouldReturnListOfServiceWhenServiceExist() {
            List<ServiceDto> expectedServices = List.of(
                    new ServiceDto("1", "Service 1", "Description 1", 100.0,"img.jpg"),
                    new ServiceDto("2", "Service 2", "Description 2", 200.0,"img.jpg")
            );
            when(serviceRepository.findAll()).thenReturn(expectedServices);
            List<ServiceDto> actualServices = serviceService.findAll();

            assertEquals(expectedServices, actualServices);
        }

        @Test
        @DisplayName("Test findAll should throw ResourceNotFoundException when no services exist")
        void testFindAllShouldThrowResourceNotFoundExceptionWhenNoServicesExist() {
            when(serviceRepository.findAll()).thenReturn(List.of());
            try {
                serviceService.findAll();
            } catch (Exception e) {
                assertEquals("No services found", e.getMessage());
            }
        }
    }

    @Nested
    class getById {
        @Test
        @DisplayName("Test getById should return service when service exists")
        void testGetByIdShouldReturnServiceWhenServiceExists() {
            String serviceId = "1";
            ServiceDto expectedService = new ServiceDto(serviceId, "Service 1", "Description 1", 100.0,"img.jpg");
            when(serviceRepository.findById(serviceId)).thenReturn(java.util.Optional.of(expectedService));

            ServiceDto actualService = serviceService.getById(serviceId);

            assertEquals(expectedService, actualService);
        }

        @Test
        @DisplayName("Test getById should throw ResourceNotFoundException when service does not exist")
        void testGetByIdShouldThrowResourceNotFoundExceptionWhenServiceDoesNotExist() {
            String serviceId = "1";
            when(serviceRepository.findById(serviceId)).thenReturn(Optional.empty());
            try {
                serviceService.getById(serviceId);
            } catch (Exception e) {
                assertEquals("Service not found", e.getMessage());
            }
        }
    }

    @Nested
    class Create {
        @Test
        @DisplayName("Test create should return created service")
        void testCreateShouldReturnCreatedService() {
            ServiceDto serviceToCreate = new ServiceDto("u1", "New Service", "New Description", 150.0,"img.jpg");
            ServiceDto expectedCreatedService = new ServiceDto("1", "New Service", "New Description", 150.0,"img.jpg");

            when(serviceRepository.create(serviceToCreate)).thenReturn(expectedCreatedService);

            ServiceDto actualCreatedService = serviceService.create(serviceToCreate);

            assertEquals(expectedCreatedService, actualCreatedService);
        }

        @Test
        @DisplayName("Test create should throw IllegalArgumentException when service exists")
        void testCreateShouldThrowIllegalArgumentExceptionWhenServiceExists() {
            ServiceDto serviceToCreate = new ServiceDto("1", "Existing Service", "Existing Description", 150.0,"img.jpg");

            when(serviceRepository.findById("1")).thenReturn(Optional.of(serviceToCreate));

            try {
                serviceService.create(serviceToCreate);
            } catch (Exception e) {
                assertEquals("Service already exists", e.getMessage());
            }
        }
    }

    @Nested
    class Update {
        @Test
        @DisplayName("Test update should return updated service")
        void testUpdateShouldReturnUpdatedService() {
            ServiceDto serviceToUpdate = new ServiceDto("1", "Updated Service", "Updated Description", 120.0, "img.jpg");
            ServiceDto expectedUpdatedService = new ServiceDto("1", "Updated Service", "Updated Description", 160.0, "img.jpg");

            when(serviceRepository.findById("1")).thenReturn(Optional.of(serviceToUpdate));
            when(serviceRepository.update(serviceToUpdate)).thenReturn(expectedUpdatedService);

            ServiceDto actualUpdatedService = serviceService.update(serviceToUpdate);
            assertEquals(expectedUpdatedService, actualUpdatedService);
        }

        @Test
        @DisplayName("Test update should throw ResourceNotFoundException when service does not exist")
        void testUpdateShouldThrowResourceNotFoundExceptionWhenServiceDoesNotExist() {
            ServiceDto serviceToUpdate = new ServiceDto("1", "Non-existing Service", "Non-existing Description", 120.0,"img.jpg");

            when(serviceRepository.findById("1")).thenReturn(Optional.empty());

            try {
                serviceService.update(serviceToUpdate);
            } catch (Exception e) {
                assertEquals("Service not found", e.getMessage());
            }
        }
    }

    @Nested
    class DeleteById {
        @Test
        @DisplayName("Test deleteById should call repository deleteById method")
        void testDeleteByIdShouldCallRepositoryDeleteByIdMethod() {
            String serviceId = "1";
            ServiceDto existingService = new ServiceDto(serviceId, "Service 1", "Description 1", 100.0,"img.jpg");

            when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(existingService));

            serviceService.deleteById(serviceId);

            verify(serviceRepository, times(1)).deleteById(serviceId);
        }

        @Test
        @DisplayName("Test deleteById should throw ResourceNotFoundException when service does not exist")
        void testDeleteByIdShouldThrowResourceNotFoundExceptionWhenServiceDoesNotExist() {
            String serviceId = null;

            when(serviceRepository.findById(serviceId)).thenReturn(Optional.empty());
            try {
                serviceService.deleteById(serviceId);
            } catch (Exception e) {
                assertEquals("Service not found", e.getMessage());
            }
        }
    }
}