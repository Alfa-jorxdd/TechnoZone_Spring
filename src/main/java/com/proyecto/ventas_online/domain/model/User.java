package com.proyecto.ventas_online.domain.model;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class User {
    private final UUID id;
    private final String name;
    private final String lastname;
    private final String email;
    private final String passwordHash;
    private final Role role;

    private User(UUID id, String name, String lastname, String email, String passwordHash, Role role) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    //Usuario NUEVO recibe el hash ya calculado
    public static User create(String name, String lastname, String email,
                              String passwordHash, Role role) {
        return new User(UUID.randomUUID(), name, lastname, email, passwordHash, role);
    }

    //Usuario EXISTENTE lo usa el mapper al leer de persistencia ojito :v
    public static User reconstitute(UUID id, String name, String lastname,
                                    String email, String passwordHash, Role role) {
        return new User(id, name, lastname, email, passwordHash, role);
    }
}
