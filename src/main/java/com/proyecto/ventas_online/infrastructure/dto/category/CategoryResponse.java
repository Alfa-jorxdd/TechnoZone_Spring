package com.proyecto.ventas_online.infrastructure.dto.category;

import java.util.UUID;

public record CategoryResponse(
        UUID id,
        String name
) {}
