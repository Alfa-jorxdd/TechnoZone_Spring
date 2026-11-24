package com.proyecto.ventas_online.domain.exception.purchase;

import com.proyecto.ventas_online.domain.exception.DomainException;
import com.proyecto.ventas_online.domain.model.PaymentStatus;

public class InvalidPurchaseTransitionException extends DomainException {
    public InvalidPurchaseTransitionException(PaymentStatus status, String action) {
        super("A purchase with a status of " + status + " cannot be " + action);
    }
}
