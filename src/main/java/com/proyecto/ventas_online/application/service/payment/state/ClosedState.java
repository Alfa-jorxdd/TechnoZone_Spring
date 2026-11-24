package com.proyecto.ventas_online.application.service.payment.state;

import com.proyecto.ventas_online.domain.exception.purchase.InvalidPurchaseTransitionException;

abstract class ClosedState implements PurchaseState {

    @Override public void complete(PurchaseContext ctx) { throw invalid("completar"); }
    @Override public void fail(PurchaseContext ctx, String reason) { throw invalid("marcar como fallida"); }
    @Override public void cancel(PurchaseContext ctx) { throw invalid("cancelar"); }

    private InvalidPurchaseTransitionException invalid(String action) {
        return new InvalidPurchaseTransitionException(status(), action);
    }
}