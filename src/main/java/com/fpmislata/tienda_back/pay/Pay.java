package com.fpmislata.tienda_back.pay;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fpmislata.tienda_back.pay.Dto.AutorizacionDto;
import com.fpmislata.tienda_back.pay.Dto.DestinoDto;
import com.fpmislata.tienda_back.pay.Dto.OrigenDto;
import com.fpmislata.tienda_back.pay.Dto.PagoDto;

import java.time.LocalDate;

public class Pay {
    AutorizacionDto autorizacion;
    OrigenDto origen;
    DestinoDto destino;
    PagoDto pago;


}
