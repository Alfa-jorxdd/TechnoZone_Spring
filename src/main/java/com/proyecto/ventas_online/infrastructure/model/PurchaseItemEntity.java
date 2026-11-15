package com.proyecto.ventas_online.infrastructure.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@ToString
@Builder
public class PurchaseItemEntity {
    private final UUID idProduct;
    private final String productName;
    private final BigDecimal unitPrice;
    private final int quantity;
}
