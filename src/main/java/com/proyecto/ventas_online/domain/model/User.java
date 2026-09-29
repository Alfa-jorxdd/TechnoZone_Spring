package com.proyecto.ventas_online.domain.model;

import java.util.UUID;

public class User {
    private final UUID id;
    private final String name;
    private final String lastname;
    private final String email;
    private final String password;
    private final Role role;

    public User(String name, String lastname, String email, String password, Role role) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        //Deberíamos hashear la contraseña
        this.password = password;
        this.role = role;
    }

    public boolean validateCredential(){
        //Falta la lógica. Podríamos usar una librería hash
        return false;
    }
}
