package com.proyecto.ventas_online.domain.exception;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }

    public static class IllegalProductStockExeption extends DomainException {
        public IllegalProductStockExeption(String message) {
            super(message);
        }
    }
}
