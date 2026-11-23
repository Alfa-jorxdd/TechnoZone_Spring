package com.proyecto.ventas_online.domain.exception.auth;

import com.proyecto.ventas_online.domain.exception.DomainException;

public class UnauthorizedActionException extends DomainException {
    public UnauthorizedActionException() {
        super("You do not have permission for this action.");
    }
}
