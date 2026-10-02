package com.proyecto.ventas_online.domain.exception.category;

public class DuplicateCategoryException extends RuntimeException {
    public DuplicateCategoryException(String name) {
        super("Category with name '" + name + "' already exists");
    }
}
