package com.proyecto.ventas_online.infrastructure.adapter.out.payment.credit_cart.dto;

import com.proyecto.ventas_online.application.ports.in.payment.PaymentInput;
import com.proyecto.ventas_online.domain.model.PaymentMethod;

public record CreditCartInput(
        String bankAccount,
        String dni
) implements PaymentInput {
    @Override
    public PaymentMethod method() {
        return PaymentMethod.CREDIT_CARD;
    }
}
