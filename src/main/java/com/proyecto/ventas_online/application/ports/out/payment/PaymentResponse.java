package com.proyecto.ventas_online.application.ports.out.payment;

import com.proyecto.ventas_online.domain.model.PaymentStatus;

public interface PaymentResponse {
    PaymentStatus getStatus();
    String getTransactionId();
    String getMessage();
    String getApprovalUrl();

    default boolean isSuccessful() {
        return getStatus() == PaymentStatus.COMPLETED;
    }
}
