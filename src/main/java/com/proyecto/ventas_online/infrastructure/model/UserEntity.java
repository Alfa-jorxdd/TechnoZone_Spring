package com.proyecto.ventas_online.infrastructure.model;

import com.proyecto.ventas_online.domain.model.Role;

import java.util.UUID;

public class UserEntity {
    private final UUID id;
    private final String name;
    private final String lastname;
    private final String email;
    private final String password;
    private final Role role;

    public UserEntity(String name, String lastname, String email, String password, Role role) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
