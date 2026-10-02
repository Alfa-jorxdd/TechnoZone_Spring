package com.proyecto.ventas_online.domain.exception.cart;

import com.proyecto.ventas_online.domain.exception.DomainException;

import java.util.UUID;

public class CartNotFoundException extends DomainException {
    public CartNotFoundException() {
        super("Cart with not found");
    }
}
