package com.proyecto.ventas_online.domain.exception.purchase;

import com.proyecto.ventas_online.domain.exception.DomainException;

public class PurchaseNotPendingException extends DomainException {
    public PurchaseNotPendingException() {
        super("The purchase is not pending approval.");
    }
}
