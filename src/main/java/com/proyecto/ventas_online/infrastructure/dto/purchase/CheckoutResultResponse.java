package com.proyecto.ventas_online.infrastructure.dto.purchase;

public record CheckoutResultResponse(
        PurchaseResponse purchase,
        String approvalUrl        // null en pagos de un solo paso
) {}
