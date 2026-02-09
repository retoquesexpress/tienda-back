package com.fpmislata.tienda_back.controller;

import com.fpmislata.tienda_back.controller.webModel.request.PayRequest;
import com.fpmislata.tienda_back.domain.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PayController {
    private final PaymentService paymentService;

    public PayController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/card")
    public ResponseEntity<Void> payment(@RequestBody PayRequest payRequest) {
        paymentService.payment(payRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
