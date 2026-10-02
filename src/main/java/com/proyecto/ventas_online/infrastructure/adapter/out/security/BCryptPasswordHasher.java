package com.proyecto.ventas_online.infrastructure.adapter.out.security;

import com.proyecto.ventas_online.application.ports.out.PasswordHasher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordHasher implements PasswordHasher {

    private final BCryptPasswordEncoder hasher = new BCryptPasswordEncoder();

    @Override
    public String encode(String rawPassword) {
        return hasher.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String hash) {
        return hasher.matches(rawPassword, hash);
    }
}
