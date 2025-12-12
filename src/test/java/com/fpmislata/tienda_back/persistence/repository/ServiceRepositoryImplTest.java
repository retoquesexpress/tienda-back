package com.fpmislata.tienda_back.persistence.repository;

import com.fpmislata.tienda_back.persistence.dao.jpa.ServiceJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.ServiceJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceRepositoryImplTest {

    @Mock
    private ServiceJpaDao serviceJpaDao;
    @InjectMocks
    private ServiceRepositoryImpl serviceRepositoryImpl;

    //SIN TERMINAR
    @Nested
    class FindAll {
        @Test
        @DisplayName("Test findAll should return list of services when services exist")
        void testFindAllShouldReturnListOfServicesWhenServicesExist() {
        /*    ServiceJpaEntity entity1 = new ServiceJpaEntity();
            entity1.setId_service("1");
            entity1.setName("Service 1");
            entity1.setDescription("Description 1");
            entity1.setPrice(100.0);
            entity1.setPictureUrl("img1.jpg");

            ServiceJpaEntity entity2 = new ServiceJpaEntity();
            entity2.setId_service("2");
            entity2.setName("Service 2");
            entity2.setDescription("Description 2");
            entity2.setPrice(200.0);
            entity2.setPictureUrl("img2.jpg");

            List<ServiceJpaEntity> mockEntities = List.of(entity1, entity2);

            when(serviceJpaDao.findAll()).thenReturn(mockEntities);
            */
        }
    }

    @Nested
    class GetById {}

    @Nested
    class FindById {}

    @Nested
    class Update {}

    @Nested
    class Create {}

    @Nested
    class DeleteById {}


}