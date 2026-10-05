package com.proyecto.ventas_online.domain.factory;

import com.proyecto.ventas_online.application.ports.out.PasswordHasher;
import com.proyecto.ventas_online.domain.model.Role;
import com.proyecto.ventas_online.domain.model.User;
import com.proyecto.ventas_online.infrastructure.adapter.out.security.BCryptPasswordHasher;
import javax.swing.*;
import java.util.List;

public class DefaultUserFactory {
    public static List<User> createDefaultUsers(){
        return List.of(
                User.create("Anghelo", "Arrunategui", "anghelo@gmail.com", "anghelo123", Role.ADMIN),
                User.create("Fernando", "Ludeña", "fernando@gmail.com", "fernando123", Role.CLIENT),
                User.create("Armando", "Patiño", "armando@gmail.com", "armando123", Role.CLIENT)
        );
    }
}
