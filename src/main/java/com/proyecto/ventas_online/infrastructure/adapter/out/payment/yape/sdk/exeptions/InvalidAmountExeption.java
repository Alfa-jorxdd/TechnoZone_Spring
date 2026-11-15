package com.proyecto.ventas_online.infrastructure.adapter.out.payment.yape.sdk.exeptions;

import java.math.BigDecimal;

public class InvalidAmountExeption extends RuntimeException {
    public InvalidAmountExeption(BigDecimal amount) {
        super("The amount '" + amount.toString() + "' is invalid");
    }
}
