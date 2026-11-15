package com.proyecto.ventas_online.application.service.payment.status;

import com.proyecto.ventas_online.application.ports.in.payment.PaymentStatusUseCase;
import com.proyecto.ventas_online.application.ports.out.PurchaseRepositoryPort;
import com.proyecto.ventas_online.domain.model.PaymentStatus;
import com.proyecto.ventas_online.domain.model.Purchase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PendingApprovalPayment implements PaymentStatusUseCase {

    private final PurchaseRepositoryPort purchaseRepositoryPort;

    @Override
    public void handle(Purchase purchase) {
        purchaseRepositoryPort.save(purchase);
    }

    @Override
    public PaymentStatus getStatus() {
        return PaymentStatus.PENDING_APPROVAL;
    }
}
