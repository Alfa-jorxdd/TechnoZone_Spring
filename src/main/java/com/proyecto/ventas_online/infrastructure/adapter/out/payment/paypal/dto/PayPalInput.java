package com.proyecto.ventas_online.infrastructure.adapter.out.payment.paypal.dto;

import com.proyecto.ventas_online.application.ports.in.payment.PaymentInput;
import com.proyecto.ventas_online.domain.model.PaymentMethod;

public record PayPalInput() implements PaymentInput {
    @Override public PaymentMethod method() { return PaymentMethod.PAYPAL; }
}
