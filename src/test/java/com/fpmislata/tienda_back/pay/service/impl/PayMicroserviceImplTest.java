package com.fpmislata.tienda_back.pay.service.impl;

import com.fpmislata.tienda_back.pay.Dto.AutorizacionDto;
import com.fpmislata.tienda_back.pay.Dto.DestinoDto;
import com.fpmislata.tienda_back.pay.Dto.OrigenDto;
import com.fpmislata.tienda_back.pay.Dto.PagoDto;
import com.fpmislata.tienda_back.pay.PaymentRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PayMicroserviceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PayMicroserviceImpl payMicroservice;

    @Test
    @DisplayName("Test payment should call restTemplate.postForEntity")
    void testPayment_ShouldCallRestTemplatePostForEntity() {
        AutorizacionDto auth = new AutorizacionDto("login", "token");
        OrigenDto origin = new OrigenDto("1234", LocalDate.now(), 123, "Name");
        DestinoDto destination = new DestinoDto("IBAN");
        PagoDto payment = new PagoDto(100, "Concept");
        PaymentRequest request = new PaymentRequest(auth, origin, destination, payment);

        payMicroservice.payment(request);

        verify(restTemplate, times(1)).postForEntity(
                eq("http://localhost:8081/api/pagos/tarjeta"),
                eq(request),
                eq(Void.class)
        );
    }
}
