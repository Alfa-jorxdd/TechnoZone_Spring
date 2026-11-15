package com.proyecto.ventas_online.application.ports.out.payment;

import com.proyecto.ventas_online.application.ports.in.payment.PaymentInput;
import com.proyecto.ventas_online.domain.model.PaymentMethod;

import java.math.BigDecimal;

public interface PaymentGatewayPort<Rq extends PaymentRequest, Rp extends  PaymentResponse> {
    PaymentMethod method();
    Rq buildRequest(PaymentInput input, BigDecimal amount);
    Rp charge(Rq request);
}
