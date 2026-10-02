package com.proyecto.ventas_online.infrastructure.dto.product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        UUID idCategory,
        String imagePath,
        Integer stock
) {}
