package com.proyecto.ventas_online.domain.exception.cart;

import com.proyecto.ventas_online.domain.exception.DomainException;

public class InvalidQuantityException extends DomainException {
    public InvalidQuantityException() {
        super("Quantity is invalid");
    }
}
