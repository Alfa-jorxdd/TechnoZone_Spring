package com.proyecto.ventas_online.domain.exception.user;

import com.proyecto.ventas_online.domain.exception.DomainException;

import java.util.UUID;

public class UserNotFoundException extends DomainException {
    public UserNotFoundException() {
        super("User not found");
    }
}
