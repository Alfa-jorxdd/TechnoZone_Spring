package com.proyecto.ventas_online.infrastructure.adapter.out.payment.yape.dto;

import com.proyecto.ventas_online.application.ports.out.payment.PaymentRequest;
import com.proyecto.ventas_online.domain.model.PaymentMethod;

import java.math.BigDecimal;

public record YapeRequest(String numero, BigDecimal monto) implements PaymentRequest {

    @Override
    public BigDecimal amount() {
        return monto;
    }

    @Override
    public PaymentMethod method() {
        return PaymentMethod.YAPE;
    }
}
