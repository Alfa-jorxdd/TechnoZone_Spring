package com.proyecto.ventas_online.infrastructure.dto.purchase;

import com.proyecto.ventas_online.domain.model.Purchase;

public record CheckoutResponse(Purchase purchase, String approvalUrl) {
}
