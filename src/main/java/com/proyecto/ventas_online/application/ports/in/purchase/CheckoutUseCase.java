package com.proyecto.ventas_online.application.ports.in.purchase;

import com.proyecto.ventas_online.application.ports.in.payment.PaymentInput;
import com.proyecto.ventas_online.domain.response.CheckoutResponse;

public interface CheckoutUseCase {
    CheckoutResponse checkout(PaymentInput input);
}
