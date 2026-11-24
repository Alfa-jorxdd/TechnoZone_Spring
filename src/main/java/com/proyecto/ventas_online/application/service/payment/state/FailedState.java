package com.proyecto.ventas_online.application.service.payment.state;

import com.proyecto.ventas_online.domain.model.PaymentStatus;

public class FailedState extends ClosedState {
    @Override public PaymentStatus status() { return PaymentStatus.FAILED; }
}
