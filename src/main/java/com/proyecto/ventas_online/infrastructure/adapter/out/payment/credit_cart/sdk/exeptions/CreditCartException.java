package com.proyecto.ventas_online.infrastructure.adapter.out.payment.credit_cart.sdk.exeptions;

public class CreditCartException extends RuntimeException {
    public CreditCartException(String message) {
        super(message);
    }
}
