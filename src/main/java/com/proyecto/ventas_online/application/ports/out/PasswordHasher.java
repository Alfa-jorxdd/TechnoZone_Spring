package com.proyecto.ventas_online.application.ports.out;

public interface PasswordHasher {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String hash);
}
