package com.proyecto.ventas_online.domain.exception.payment;

import com.proyecto.ventas_online.domain.exception.DomainException;

public class InvalidPaymentInputException extends DomainException {
    public InvalidPaymentInputException(String message) {
        super(message);
    }
}
