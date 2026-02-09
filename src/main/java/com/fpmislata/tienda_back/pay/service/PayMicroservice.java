package com.fpmislata.tienda_back.pay.service;

import com.fpmislata.tienda_back.pay.PaymentRequest;

public interface PayMicroservice {
    void payment(PaymentRequest paymentRequest);
}
