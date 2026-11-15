package com.proyecto.ventas_online.application.service.payment.status;

import com.proyecto.ventas_online.application.ports.in.payment.PaymentStatusUseCase;
import com.proyecto.ventas_online.application.ports.out.PurchaseRepositoryPort;
import com.proyecto.ventas_online.domain.model.PaymentStatus;
import com.proyecto.ventas_online.domain.model.Purchase;
import com.proyecto.ventas_online.infrastructure.adapter.out.payment.paypal.exception.PaymentFailedException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FailedPayment implements PaymentStatusUseCase{

    private final PurchaseRepositoryPort purchaseRepository;

    @Override
    public void handle(Purchase purchase) {
        purchase.markFailed();
        purchaseRepository.save(purchase);
        throw new PaymentFailedException("Payment failed");
    }

    @Override
    public PaymentStatus getStatus() {
        return PaymentStatus.FAILED;
    }
}
