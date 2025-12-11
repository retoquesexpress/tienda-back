package com.fpmislata.tienda_back.config;

import com.fpmislata.tienda_back.domain.repository.ServiceRepository;
import com.fpmislata.tienda_back.domain.service.ServiceService;
import com.fpmislata.tienda_back.domain.service.impl.ServiceServiceImpl;
import com.fpmislata.tienda_back.persistence.dao.jpa.ServiceJpaDao;
import com.fpmislata.tienda_back.persistence.repository.ServiceRepositoryImpl;
import org.springframework.context.annotation.Bean;

public class SpringConfig {






    @Bean
    public ServiceRepository serviceRepository(ServiceJpaDao serviceJpaDao) {
        return new ServiceRepositoryImpl(serviceJpaDao);
    }

    @Bean
    public ServiceService serviceService(ServiceRepository serviceRepository) {
        return new ServiceServiceImpl(serviceRepository);
    }

}
