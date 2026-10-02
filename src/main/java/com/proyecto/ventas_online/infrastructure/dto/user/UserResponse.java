package com.proyecto.ventas_online.infrastructure.dto.user;

import com.proyecto.ventas_online.domain.model.Role;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String lastname,
        String email,
        Role role
) {}
