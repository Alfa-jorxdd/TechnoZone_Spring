package com.proyecto.ventas_online.domain.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Category {
    private final UUID id;
    private final String name;

    public Category(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
