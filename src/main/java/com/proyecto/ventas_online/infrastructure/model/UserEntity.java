package com.proyecto.ventas_online.infrastructure.model;

import com.proyecto.ventas_online.domain.model.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@Builder
public class UserEntity {
    private final UUID id;
    private final String name;
    private final String lastname;
    private final String email;
    private final String passwordHash;
    private final Role role;

    public UserEntity(String name, String lastname, String email, String passwordHash, Role role) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }
}
