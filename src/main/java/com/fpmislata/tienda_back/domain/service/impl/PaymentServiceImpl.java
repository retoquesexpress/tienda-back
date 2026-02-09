package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.controller.webModel.request.PayRequest;
import com.fpmislata.tienda_back.domain.service.PaymentService;
import com.fpmislata.tienda_back.pay.Dto.AutorizacionDto;
import com.fpmislata.tienda_back.pay.Dto.DestinoDto;
import com.fpmislata.tienda_back.pay.service.PayMicroservice;
import com.fpmislata.tienda_back.pay.PaymentRequest;

public class PaymentServiceImpl implements PaymentService {
    private final PayMicroservice paymentMicroservice;

    public PaymentServiceImpl(PayMicroservice paymentMicroservice) {
        this.paymentMicroservice = paymentMicroservice;
    }

    @Override
    public void payment(PayRequest payRequest) {
        PaymentRequest pay = new PaymentRequest(
                new AutorizacionDto("retoquesexpress","token888"),
                payRequest.origen(),
                new DestinoDto("ES1111111111111111111111"),
                payRequest.pago()
        );
        paymentMicroservice.payment(pay);
    }
}
