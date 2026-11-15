package com.proyecto.ventas_online.infrastructure.adapter.out.payment.credit_cart.dto;

import com.proyecto.ventas_online.application.ports.out.payment.PaymentRequest;
import com.proyecto.ventas_online.domain.model.PaymentMethod;

import java.math.BigDecimal;

public record CreditCartRequest(
        String bankAccount,
        String dni,
        BigDecimal amount
) implements PaymentRequest {
    @Override
    public BigDecimal amount() {
        return amount;
    }

    @Override
    public PaymentMethod method() {
        return PaymentMethod.CREDIT_CARD;
    }
}
