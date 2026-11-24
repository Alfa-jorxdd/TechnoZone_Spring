package com.proyecto.ventas_online.application.service.payment.state;

import com.proyecto.ventas_online.domain.model.PaymentStatus;
import com.proyecto.ventas_online.infrastructure.adapter.out.payment.paypal.exception.PaymentFailedException;

public class PendingState implements PurchaseState {

    @Override public PaymentStatus status() {
        return PaymentStatus.PENDING_APPROVAL;
    }
    @Override public boolean canConfirm() {
        return true;
    }

    @Override
    public void complete(PurchaseContext ctx) {
        ctx.discountStock();
        ctx.clearCart();
        ctx.moveTo(new PaidState());
    }

    @Override
    public void fail(PurchaseContext ctx, String reason) {
        ctx.moveTo(new FailedState());
        throw new PaymentFailedException(reason);
    }

    @Override
    public void cancel(PurchaseContext ctx) {
        ctx.moveTo(new CancelledState());
    }
}
