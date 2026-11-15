package com.proyecto.ventas_online.application.ports.in.payment;

import com.proyecto.ventas_online.domain.model.PaymentStatus;
import com.proyecto.ventas_online.domain.model.Purchase;

public interface PaymentStatusUseCase {
    void handle(Purchase purchase);
    PaymentStatus getStatus();
}
