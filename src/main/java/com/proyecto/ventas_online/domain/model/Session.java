package com.proyecto.ventas_online.domain.model;

import java.util.UUID;

public record Session(UUID userId, Role role) {}