package com.fpmislata.tienda_back.domain.service;

import com.fpmislata.tienda_back.controller.webModel.request.PayRequest;
import com.fpmislata.tienda_back.pay.PayMicroservice;

public interface PaymentService {
    void payment(PayRequest payRequest);
}
