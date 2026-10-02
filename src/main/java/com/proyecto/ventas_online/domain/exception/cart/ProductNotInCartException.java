package com.proyecto.ventas_online.domain.exception.cart;

import com.proyecto.ventas_online.domain.exception.DomainException;

import java.util.UUID;

public class ProductNotInCartException extends DomainException {
    public ProductNotInCartException(UUID id) {
        super("Product with ID " + id + "not included in the cart");
    }
}
