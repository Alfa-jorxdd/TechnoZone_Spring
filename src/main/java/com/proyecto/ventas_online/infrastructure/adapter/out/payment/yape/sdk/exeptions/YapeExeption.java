package com.proyecto.ventas_online.infrastructure.adapter.out.payment.yape.sdk.exeptions;

public class YapeExeption extends RuntimeException {
    public YapeExeption(String message) {
        super(message);
    }
}
