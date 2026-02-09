package com.fpmislata.tienda_back.domain.service;

import com.fpmislata.tienda_back.controller.webModel.request.PayRequest;

public interface PaymentService {
    void payment(PayRequest payRequest);
}
