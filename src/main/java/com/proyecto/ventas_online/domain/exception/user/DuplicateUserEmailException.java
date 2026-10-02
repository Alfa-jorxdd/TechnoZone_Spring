package com.proyecto.ventas_online.domain.exception.user;

import com.proyecto.ventas_online.domain.exception.DomainException;

public class DuplicateUserEmailException extends DomainException {
    public DuplicateUserEmailException(String email) {
        super("User with email '" + email + "' already exists");
    }
}
