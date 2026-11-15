package com.proyecto.ventas_online.infrastructure.adapter.out.payment.credit_cart.sdk.exeptions;

public class InvalidAmountCreditCartException extends CreditCartException {
    public InvalidAmountCreditCartException() {
        super("Amount invalid");
    }
}
