package com.proyecto.ventas_online.infrastructure.dto.product;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name cannot exceed 50 characters")
        String name,

        @NotBlank(message = "Description is requeried")
        @Size(max = 500, message = "Name cannot exceed 500 characters")
        String description,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be greater than 0")
        BigDecimal price,

        @NotNull(message = "Category is requeried")
        UUID idCategory,

        String imagePath,

        @NotNull(message = "El stock es obligatorio")
        @PositiveOrZero(message = "El stock no puede ser negativo")
        Integer stock
) {}
