package com.proyecto.ventas_online.application.ports.out.payment;

import com.proyecto.ventas_online.domain.model.PaymentMethod;

import java.math.BigDecimal;

public interface PaymentRequest  {
    BigDecimal amount();
    PaymentMethod method();
}
