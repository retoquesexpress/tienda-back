package com.fpmislata.tienda_back.config;

import com.fpmislata.tienda_back.domain.repository.AuthRepository;
import com.fpmislata.tienda_back.domain.repository.BookingItemRepository;
import com.fpmislata.tienda_back.domain.repository.BookingRepository;

import com.fpmislata.tienda_back.domain.repository.CategoryRepository;
import com.fpmislata.tienda_back.domain.repository.ServiceRepository;
import com.fpmislata.tienda_back.domain.repository.UserRepository;
import com.fpmislata.tienda_back.domain.service.*;

import com.fpmislata.tienda_back.domain.service.impl.*;

import com.fpmislata.tienda_back.pay.PayMicroservice;
import com.fpmislata.tienda_back.pay.PayMicroserviceImpl;
import com.fpmislata.tienda_back.persistence.PersistenceConfig;
import com.fpmislata.tienda_back.persistence.dao.jpa.BookingJpaDao;

import com.fpmislata.tienda_back.persistence.dao.jpa.BookingItemJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.CategoryJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.ServiceJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.UserJpaDao;
import com.fpmislata.tienda_back.persistence.repository.AuthRepositoryImpl;
import com.fpmislata.tienda_back.persistence.repository.BookingItemRepositoryImpl;
import com.fpmislata.tienda_back.persistence.repository.BookingRepositoryImpl;

import com.fpmislata.tienda_back.persistence.repository.CategoryRepositoryImpl;
import com.fpmislata.tienda_back.persistence.repository.ServiceRepositoryImpl;
import com.fpmislata.tienda_back.persistence.repository.UserRepositoryImpl;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.fpmislata.tienda_back.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

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

    @Bean
    public AuthService authService(AuthRepository authRepository) {
        return new AuthServiceImpl(authRepository);
    }

    @Bean
    public AuthRepository authRepository(UserJpaDao userJpaDao) {
        return new AuthRepositoryImpl(userJpaDao);
    }

    @Bean
    public BookingItemRepository bookingItemRepository(BookingItemJpaDao bookingItemJpaDao) {
        return new BookingItemRepositoryImpl(bookingItemJpaDao);
    }

    @Bean
    public BookingItemService bookingItemService(BookingItemRepository bookingItemRepository) {
        return new BookingItemServiceImpl(bookingItemRepository);
    }

    @Bean
    public BookingRepository bookingRepository(BookingJpaDao bookingJpaDao, ServiceJpaDao serviceJpaDao) {
        return new BookingRepositoryImpl(bookingJpaDao, serviceJpaDao);
    }

    @Bean
    public BookingService bookingService(BookingRepository bookingRepository) {
        return new BookingServiceImpl(bookingRepository);
    }

    @Bean

    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter(AuthService authService) {
        FilterRegistrationBean<AuthFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new AuthFilter(authService));

        // RUTAS PROTEGIDAS
        registration.addUrlPatterns("/api/*");

        registration.setOrder(1);
        return registration;
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public PayMicroservice payMicroservice(RestTemplate restTemplate) {
        return new PayMicroserviceImpl(restTemplate);
    }

    @Bean
    public PaymentService paymentService(PayMicroservice payMicroservice) {
        return new PaymentServiceImpl(payMicroservice);
    }

}
