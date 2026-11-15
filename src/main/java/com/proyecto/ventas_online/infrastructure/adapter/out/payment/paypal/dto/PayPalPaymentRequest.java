package com.proyecto.ventas_online.infrastructure.adapter.out.payment.paypal.dto;

import com.proyecto.ventas_online.application.ports.out.payment.PaymentRequest;
import com.proyecto.ventas_online.domain.model.PaymentMethod;

import java.math.BigDecimal;

public record PayPalPaymentRequest(BigDecimal amount) implements PaymentRequest {
    @Override
    public PaymentMethod method() {
        return PaymentMethod.PAYPAL;
    }
}
