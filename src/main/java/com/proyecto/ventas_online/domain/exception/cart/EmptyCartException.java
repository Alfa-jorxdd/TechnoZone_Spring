package com.proyecto.ventas_online.domain.exception.cart;

import com.proyecto.ventas_online.domain.exception.DomainException;

public class EmptyCartException extends DomainException {
    public EmptyCartException() {
        super("Tha cart is empty");
    }
}
