package com.fpmislata.tienda_back.pay;

import com.fpmislata.tienda_back.controller.webModel.request.PayRequest;

public interface PayMicroservice {
    void payment(PaymentRequest paymentRequest);
}
