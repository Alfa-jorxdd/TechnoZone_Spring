package com.proyecto.ventas_online.infrastructure.adapter.out.payment.paypal.dto;

import com.proyecto.ventas_online.application.ports.out.payment.PaymentResponse;
import com.proyecto.ventas_online.domain.model.PaymentStatus;

public class PayPalPaymentResponse implements PaymentResponse {

    private final PaymentStatus status;
    private final String transactionId;
    private final String message;
    private final String approvalUrl;

    public PayPalPaymentResponse(PaymentStatus status, String transactionId,
                                 String message, String approvalUrl) {
        this.status = status;
        this.transactionId = transactionId;
        this.message = message;
        this.approvalUrl = approvalUrl;
    }

    @Override public PaymentStatus getStatus() { return status; }
    @Override public String getTransactionId() { return transactionId; }
    @Override public String getMessage() { return message; }
    @Override public String getApprovalUrl() { return approvalUrl; }
}
