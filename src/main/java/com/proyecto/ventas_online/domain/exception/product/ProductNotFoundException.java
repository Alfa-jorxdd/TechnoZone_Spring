package com.proyecto.ventas_online.domain.exception.product;

import com.proyecto.ventas_online.domain.exception.DomainException;

import java.util.UUID;

public class ProductNotFoundException extends DomainException {
    public ProductNotFoundException(UUID id) {
        super("Product with ID " + id + " not found");
    }
}
