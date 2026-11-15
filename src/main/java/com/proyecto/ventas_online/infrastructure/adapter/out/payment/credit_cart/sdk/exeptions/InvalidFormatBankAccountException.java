package com.proyecto.ventas_online.infrastructure.adapter.out.payment.credit_cart.sdk.exeptions;

public class InvalidFormatBankAccountException extends CreditCartException {
    public InvalidFormatBankAccountException(String bankAccount) {
        super("Format from " + bankAccount + " is invalid");
    }
}
