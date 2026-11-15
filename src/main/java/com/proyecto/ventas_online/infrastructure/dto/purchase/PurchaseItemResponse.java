package com.proyecto.ventas_online.infrastructure.dto.purchase;

import java.math.BigDecimal;
import java.util.UUID;

public record PurchaseItemResponse(
        UUID idProduct,
        String productName,
        BigDecimal unitPrice,
        int quantity,
        BigDecimal subtotal
) {}
