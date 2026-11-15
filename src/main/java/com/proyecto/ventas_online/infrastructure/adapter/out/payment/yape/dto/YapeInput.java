package com.proyecto.ventas_online.infrastructure.adapter.out.payment.yape.dto;

import com.proyecto.ventas_online.application.ports.in.payment.PaymentInput;
import com.proyecto.ventas_online.domain.exception.payment.InvalidPaymentInputException;
import com.proyecto.ventas_online.domain.model.PaymentMethod;

import java.math.BigDecimal;

public record YapeInput(String numberPhone) implements PaymentInput {

    public YapeInput {
        if (numberPhone == null || !numberPhone.matches("\\d{9}")) {
            throw new InvalidPaymentInputException("The phone number must have 9 digits.");
        }
    }
    @Override
    public PaymentMethod method() {
        return PaymentMethod.YAPE;
    }
}
