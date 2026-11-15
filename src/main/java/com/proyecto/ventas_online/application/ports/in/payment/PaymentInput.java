package com.proyecto.ventas_online.application.ports.in.payment;

import com.proyecto.ventas_online.domain.model.PaymentMethod;

public interface PaymentInput {
    PaymentMethod method();
}
