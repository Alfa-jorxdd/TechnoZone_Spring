package com.proyecto.ventas_online.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record PurchaseItem(
        UUID idProduct,
        String productName,
        BigDecimal unitPrice,
        int quantity) {}
