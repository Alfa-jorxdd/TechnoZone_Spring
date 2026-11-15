package com.proyecto.ventas_online.infrastructure.adapter.out.payment.paypal.exception;

import com.proyecto.ventas_online.domain.exception.DomainException;

public class PaymentFailedException extends DomainException {
    public PaymentFailedException(String message) {
        super(message);
    }

    public PaymentFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
