package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.controller.webModel.request.PayRequest;
import com.fpmislata.tienda_back.pay.Dto.AutorizacionDto;
import com.fpmislata.tienda_back.pay.Dto.DestinoDto;
import com.fpmislata.tienda_back.pay.Dto.OrigenDto;
import com.fpmislata.tienda_back.pay.Dto.PagoDto;
import com.fpmislata.tienda_back.pay.PaymentRequest;
import com.fpmislata.tienda_back.pay.service.PayMicroservice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private PayMicroservice payMicroservice;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    @DisplayName("Test payment should call payMicroservice.payment with mapped request")
    void testPayment_ShouldCallPayMicroservicePayment_WithMappedRequest() {
        OrigenDto origin = new OrigenDto("1234", LocalDate.now(), 123, "Name");
        PagoDto payment = new PagoDto(100, "Concept");
        PayRequest payRequest = new PayRequest(origin, payment);

        paymentService.payment(payRequest);

        ArgumentCaptor<PaymentRequest> captor = ArgumentCaptor.forClass(PaymentRequest.class);
        verify(payMicroservice, times(1)).payment(captor.capture());

        PaymentRequest capturedRequest = captor.getValue();

        assertAll(
                () -> assertEquals("retoquesexpress", capturedRequest.autorizacion().login()),
                () -> assertEquals("token888", capturedRequest.autorizacion().apiToken()),
                () -> assertEquals(origin, capturedRequest.origen()),
                () -> assertEquals("ES1111111111111111111111", capturedRequest.destino().iban()),
                () -> assertEquals(payment, capturedRequest.pago())
        );
    }
}
