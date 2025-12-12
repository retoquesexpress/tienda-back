package com.fpmislata.tienda_back.config;

import com.fpmislata.tienda_back.domain.repository.CategoryRepository;
import com.fpmislata.tienda_back.domain.repository.ServiceRepository;
import com.fpmislata.tienda_back.domain.repository.UserRepository;
import com.fpmislata.tienda_back.domain.service.CategoryService;
import com.fpmislata.tienda_back.domain.service.ServiceService;
import com.fpmislata.tienda_back.domain.service.UserService;
import com.fpmislata.tienda_back.domain.service.impl.CategoryServiceImpl;
import com.fpmislata.tienda_back.domain.service.impl.ServiceServiceImpl;
import com.fpmislata.tienda_back.domain.service.impl.UserServiceImpl;
import com.fpmislata.tienda_back.persistence.PersistenceConfig;
import com.fpmislata.tienda_back.persistence.dao.jpa.CategoryJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.ServiceJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.UserJpaDao;
import com.fpmislata.tienda_back.persistence.repository.CategoryRepositoryImpl;
import com.fpmislata.tienda_back.persistence.repository.ServiceRepositoryImpl;
import com.fpmislata.tienda_back.persistence.repository.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceConfig.class)
public class SpringConfig {

    @Bean
    public CategoryRepository categoryRepository(CategoryJpaDao categoryJpaDao) {
        return new CategoryRepositoryImpl(categoryJpaDao);
    }
    @Bean
    public CategoryService categoryService(CategoryRepository categoryRepository) {
        return new CategoryServiceImpl(categoryRepository);
    }

    @Bean
    public UserRepository userRepository(UserJpaDao userJpaDao) {
        return new UserRepositoryImpl(userJpaDao);
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }


    @Bean
    public ServiceRepository serviceRepository(ServiceJpaDao serviceJpaDao) {
        return new ServiceRepositoryImpl(serviceJpaDao);
    }

    @Bean
    public ServiceService serviceService(ServiceRepository serviceRepository) {
        return new ServiceServiceImpl(serviceRepository);
    }

}
