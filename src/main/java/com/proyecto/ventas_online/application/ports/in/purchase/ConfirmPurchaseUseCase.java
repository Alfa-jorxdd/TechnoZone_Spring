package com.proyecto.ventas_online.application.ports.in.purchase;

import com.proyecto.ventas_online.domain.model.Purchase;

public interface ConfirmPurchaseUseCase {
    Purchase confirm(String transactionId);
}
