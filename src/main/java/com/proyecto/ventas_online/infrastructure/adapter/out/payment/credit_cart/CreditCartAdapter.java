package com.proyecto.ventas_online.infrastructure.adapter.out.payment.credit_cart;

import com.proyecto.ventas_online.application.ports.in.payment.PaymentInput;
import com.proyecto.ventas_online.application.ports.out.payment.PaymentGatewayPort;
import com.proyecto.ventas_online.domain.model.PaymentMethod;
import com.proyecto.ventas_online.domain.model.PaymentStatus;
import com.proyecto.ventas_online.infrastructure.adapter.out.payment.credit_cart.dto.CreditCartInput;
import com.proyecto.ventas_online.infrastructure.adapter.out.payment.credit_cart.dto.CreditCartRequest;
import com.proyecto.ventas_online.infrastructure.adapter.out.payment.credit_cart.dto.CreditCartResponse;
import com.proyecto.ventas_online.infrastructure.adapter.out.payment.credit_cart.sdk.CreditCartSdk;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CreditCartAdapter implements PaymentGatewayPort<CreditCartRequest, CreditCartResponse> {
    private final CreditCartSdk creditCartSdk;

    @Override
    public PaymentMethod method() {
        return PaymentMethod.CREDIT_CARD;
    }

    @Override
    public CreditCartRequest buildRequest(PaymentInput input, BigDecimal amount) {
        if (!(input instanceof CreditCartInput(String bankAccount, String dni))){
            throw new IllegalArgumentException("CreditCartAdapter requiere CreditCartInput");
        }
        return new CreditCartRequest(bankAccount, dni, amount);
    }

    @Override
    public CreditCartResponse charge(CreditCartRequest request) {
        String transactionId = creditCartSdk.pay(request.bankAccount(), request.dni(), request.amount());
        return new CreditCartResponse(
                PaymentStatus.COMPLETED,
                transactionId,
                "Paid the amount " + request.amount()
        );
    }
}
