package com.fpmislata.tienda_back.pay;
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
