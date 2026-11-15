package com.proyecto.ventas_online.application.ports.in.purchase;

import com.proyecto.ventas_online.domain.model.Purchase;

import java.util.UUID;

public interface GetPurchaseUseCase {
    Purchase getById(UUID purchaseId);
}
