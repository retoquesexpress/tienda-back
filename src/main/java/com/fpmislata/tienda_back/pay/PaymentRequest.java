package com.fpmislata.tienda_back.pay;

import com.fpmislata.tienda_back.pay.Dto.AutorizacionDto;
import com.fpmislata.tienda_back.pay.Dto.DestinoDto;
import com.fpmislata.tienda_back.pay.Dto.OrigenDto;
import com.fpmislata.tienda_back.pay.Dto.PagoDto;

public record PaymentRequest(
        AutorizacionDto autorizacion,
        OrigenDto origen,
        DestinoDto destino,
        PagoDto pago
) {
}
