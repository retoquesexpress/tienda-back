package com.fpmislata.tienda_back.controller.webModel.request;

import com.fpmislata.tienda_back.pay.Dto.AutorizacionDto;
import com.fpmislata.tienda_back.pay.Dto.DestinoDto;
import com.fpmislata.tienda_back.pay.Dto.OrigenDto;
import com.fpmislata.tienda_back.pay.Dto.PagoDto;

public record PayRequest(
        OrigenDto origen,
        PagoDto pago
) {
}
