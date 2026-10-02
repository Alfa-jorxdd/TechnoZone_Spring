package com.proyecto.ventas_online.domain.exception.session;

import com.proyecto.ventas_online.domain.exception.DomainException;

public class NoActiveSessionException extends DomainException {
    public NoActiveSessionException() {
        super("Session is required first");
    }
}
