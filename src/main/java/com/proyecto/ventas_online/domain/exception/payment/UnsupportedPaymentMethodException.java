package com.proyecto.ventas_online.domain.exception.payment;

import com.proyecto.ventas_online.domain.exception.DomainException;

public class UnsupportedPaymentMethodException extends DomainException {
    public UnsupportedPaymentMethodException(String method) {
        super("Método de pago no soportado: " + method);
    }
}
