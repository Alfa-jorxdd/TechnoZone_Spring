package com.proyecto.ventas_online.domain.exception.product;

import com.proyecto.ventas_online.domain.exception.DomainException;

public class IllegalProductStockException extends DomainException {
    public IllegalProductStockException() {
        super("Product stock cannot be less than 0");
    }
}
