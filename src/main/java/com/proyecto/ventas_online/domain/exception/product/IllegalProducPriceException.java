package com.proyecto.ventas_online.domain.exception.product;

import com.proyecto.ventas_online.domain.exception.DomainException;

public class IllegalProducPriceException extends DomainException {
    public IllegalProducPriceException() {
        super("Product price must be 0 or greater");
    }
}
