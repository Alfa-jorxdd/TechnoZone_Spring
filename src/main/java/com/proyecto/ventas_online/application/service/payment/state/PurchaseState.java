package com.proyecto.ventas_online.application.service.payment.state;

import com.proyecto.ventas_online.domain.model.PaymentStatus;

public interface PurchaseState {
    PaymentStatus status();
    void complete(PurchaseContext ctx);
    void fail(PurchaseContext ctx, String reason);
    void cancel(PurchaseContext ctx);
    default boolean canConfirm() { return false; }
}
