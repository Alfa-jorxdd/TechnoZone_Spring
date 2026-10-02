package com.proyecto.ventas_online.infrastructure.dto.user;

import com.proyecto.ventas_online.domain.model.Role;

public record CreateUserCommand(
        String name,
        String lastname,
        String email,
        String rawPassword,
        Role role
) {}