package com.proyecto.ventas_online.domain.exception.payment;

import com.proyecto.ventas_online.domain.exception.DomainException;
import com.proyecto.ventas_online.domain.model.PaymentMethod;

public class PaymentConfirmationNotSupportedException extends DomainException {
    public PaymentConfirmationNotSupportedException(PaymentMethod method) {
        super("El método " + method + " no requiere confirmación");
    }
}
