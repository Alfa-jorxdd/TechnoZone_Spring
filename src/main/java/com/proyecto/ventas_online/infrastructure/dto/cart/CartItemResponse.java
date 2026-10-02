package com.proyecto.ventas_online.infrastructure.dto.cart;

import java.math.BigDecimal;
import java.util.UUID;

public record CartItemResponse(
        UUID idProduct,
        int quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal
) {}
