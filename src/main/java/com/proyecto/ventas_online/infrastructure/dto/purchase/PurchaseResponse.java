package com.proyecto.ventas_online.infrastructure.dto.purchase;

import com.proyecto.ventas_online.domain.model.PaymentMethod;
import com.proyecto.ventas_online.domain.model.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PurchaseResponse(
        UUID idPurchase,
        String transactionId,
        LocalDateTime createdAt,
        PaymentMethod paymentMethod,
        PaymentStatus status,
        BigDecimal total,
        List<PurchaseItemResponse> items
) {}
