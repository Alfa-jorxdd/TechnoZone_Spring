package com.proyecto.ventas_online.infrastructure.dto.auth;

import com.proyecto.ventas_online.domain.model.Role;

import java.util.UUID;

public record SessionResponse(UUID userId, Role role) {}
