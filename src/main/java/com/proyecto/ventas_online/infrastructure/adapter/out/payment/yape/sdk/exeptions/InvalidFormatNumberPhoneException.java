package com.proyecto.ventas_online.infrastructure.adapter.out.payment.yape.sdk.exeptions;

public class InvalidFormatNumberPhoneException extends YapeExeption {
    public InvalidFormatNumberPhoneException(String nunmberPhone) {
        super("The number phone '" + nunmberPhone + "' is invalid");
    }
}
