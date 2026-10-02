package com.proyecto.ventas_online.domain.exception.auth;

import com.proyecto.ventas_online.domain.exception.DomainException;

public class InvalidCredentialsException extends DomainException {
    public InvalidCredentialsException() {
        super("Credentials invalid");
    }
}
