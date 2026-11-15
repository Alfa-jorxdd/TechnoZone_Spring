package com.proyecto.ventas_online.infrastructure.adapter.out.payment.yape.dto;

import com.proyecto.ventas_online.application.ports.out.payment.PaymentResponse;
import com.proyecto.ventas_online.domain.model.PaymentStatus;

public record YapeResponse(
        PaymentStatus status,
        String transactionId,
        String message
) implements PaymentResponse {
    @Override
    public PaymentStatus getStatus() {
        return status;
    }

    @Override
    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getApprovalUrl() {
        return "";
    }

}
