package com.proyecto.ventas_online.infrastructure.model;

import com.proyecto.ventas_online.domain.model.PaymentMethod;
import com.proyecto.ventas_online.domain.model.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@ToString
@Builder
public class PurchaseEntity {
    private final UUID idPurchase;
    private final UUID idClient;
    private final List<PurchaseItemEntity> items;
    private final BigDecimal total;
    private final PaymentMethod paymentMethod;
    private final LocalDateTime createdAt;
    private final PaymentStatus status;
    private final String transactionId;
}
