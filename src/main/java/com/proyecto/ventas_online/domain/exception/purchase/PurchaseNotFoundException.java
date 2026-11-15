package com.proyecto.ventas_online.domain.exception.purchase;

import com.proyecto.ventas_online.domain.exception.DomainException;

import java.util.UUID;

public class PurchaseNotFoundException extends DomainException {
    public PurchaseNotFoundException(String transactionId) {
        super("Purchase with id transaction '" + transactionId + "' not found");
    }

    public PurchaseNotFoundException(UUID idPurchase){
        super("Purchase with id '" + idPurchase + "' not found");
    }
}
