package com.proyecto.ventas_online.infrastructure.adapter.out.payment.yape.sdk.exeptions;

public class MaxAmountException extends RuntimeException {
    public MaxAmountException() {
        super("The maximum limit of 1,000 via Yape was exceeded.");
    }
}
