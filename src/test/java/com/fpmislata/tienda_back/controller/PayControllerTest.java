package com.fpmislata.tienda_back.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpmislata.tienda_back.controller.webModel.request.PayRequest;
import com.fpmislata.tienda_back.domain.service.PaymentService;
import com.fpmislata.tienda_back.pay.Dto.OrigenDto;
import com.fpmislata.tienda_back.pay.Dto.PagoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PayController.class)
@AutoConfigureMockMvc(addFilters = false)
class PayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Test payment endpoint should return OK status")
    void testPayment_ShouldReturnOkStatus() throws Exception {
        OrigenDto origin = new OrigenDto("1234", LocalDate.now(), 123, "Name");
        PagoDto payment = new PagoDto(100, "Concept");
        PayRequest payRequest = new PayRequest(origin, payment);

        mockMvc.perform(post("/api/payments/card")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payRequest)))
                .andExpect(status().isOk());

        verify(paymentService, times(1)).payment(payRequest);
    }
}
