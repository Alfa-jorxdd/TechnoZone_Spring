package com.proyecto.ventas_online.application.ports.out.payment;

import com.proyecto.ventas_online.domain.model.PaymentMethod;

public interface PaymentConfirmationPort<Rp extends PaymentResponse> {
    PaymentMethod method();
    Rp confirm(String transactionId);
}