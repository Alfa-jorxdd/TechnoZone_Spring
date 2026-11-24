package com.proyecto.ventas_online.application.service.payment.state;

import com.proyecto.ventas_online.application.ports.out.CartRepositoryPort;
import com.proyecto.ventas_online.application.ports.out.ProductRepositoryPort;
import com.proyecto.ventas_online.application.ports.out.PurchaseRepositoryPort;
import com.proyecto.ventas_online.domain.model.Purchase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PurchaseContextFactory {

    private final PurchaseRepositoryPort purchases;
    private final ProductRepositoryPort products;
    private final CartRepositoryPort carts;

    public PurchaseContext of(Purchase purchase) {
        PurchaseState state = switch (purchase.getStatus()) {
            case PENDING_APPROVAL -> new PendingState();
            case COMPLETED        -> new PaidState();
            case FAILED           -> new FailedState();
            case CANCELLED        -> new CancelledState();
        };
        return new PurchaseContext(purchase, state, purchases, products, carts);
    }
}
