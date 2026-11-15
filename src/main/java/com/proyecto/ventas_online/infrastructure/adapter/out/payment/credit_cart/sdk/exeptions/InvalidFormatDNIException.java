package com.proyecto.ventas_online.infrastructure.adapter.out.payment.credit_cart.sdk.exeptions;

public class InvalidFormatDNIException extends CreditCartException{

    public InvalidFormatDNIException(String dni) {
        super("Format from " + dni + " is invalid");
    }
}
