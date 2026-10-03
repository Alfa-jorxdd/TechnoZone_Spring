package com.proyecto.ventas_online.domain.factory;

import com.proyecto.ventas_online.application.ports.out.PasswordHasher;
import com.proyecto.ventas_online.domain.model.Role;
import com.proyecto.ventas_online.domain.model.User;
import com.proyecto.ventas_online.infrastructure.adapter.out.security.BCryptPasswordHasher;
import javax.swing.*;
import java.util.List;

public class DefaultUserFactory {

    private final PasswordHasher passwordHasher = new BCryptPasswordHasher();

    public List<User> createDefaultUsers(){
        String hash = passwordHasher.encode("anghelo12345");
        return List.of(
                User.create("Anghelo", "Arrunategui", "anghelo@gmail.com", hash, Role.ADMIN)
        );
    }
}
