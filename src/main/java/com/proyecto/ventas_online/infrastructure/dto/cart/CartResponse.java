package com.proyecto.ventas_online.infrastructure.dto.cart;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CartResponse(
        UUID idCart,
        UUID idClient,
        List<CartItemResponse> items,
        BigDecimal total
) {}
