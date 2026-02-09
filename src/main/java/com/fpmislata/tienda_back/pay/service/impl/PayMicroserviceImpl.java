package com.fpmislata.tienda_back.pay.service.impl;
import com.fpmislata.tienda_back.pay.service.PayMicroservice;
import com.fpmislata.tienda_back.pay.PaymentRequest;
import org.springframework.web.client.RestTemplate;

public class PayMicroserviceImpl implements PayMicroservice {
    private final RestTemplate restTemplate;

    public PayMicroserviceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void payment(PaymentRequest paymentrequest) {
        restTemplate.postForEntity("http://localhost:8081/api/pagos/tarjeta", paymentrequest, Void.class);

    }

}
