package com.proyecto.ventas_online.domain.exception.cart;

import java.util.UUID;

public class DuplicateCartException extends RuntimeException {
    public DuplicateCartException(UUID id) {
        super("Cart with user ID " + id + " already exists");
    }
}
