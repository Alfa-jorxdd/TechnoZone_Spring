package com.proyecto.ventas_online.domain.exception.category;

import com.proyecto.ventas_online.domain.exception.DomainException;

import java.util.UUID;

public class CategoryNotFoundException extends DomainException {
    public CategoryNotFoundException(UUID id) {
        super("Category with ID " + id + " not found");
    }
}
