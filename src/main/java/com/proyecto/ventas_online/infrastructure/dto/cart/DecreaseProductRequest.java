package com.proyecto.ventas_online.infrastructure.dto.cart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record DecreaseProductRequest(
        @NotNull(message = "El producto es obligatorio")
        UUID idProduct,

        @Positive(message = "La cantidad debe ser mayor que 0")
        int quantity
) {}
