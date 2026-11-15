package com.proyecto.ventas_online.application.ports.out;

import com.proyecto.ventas_online.domain.model.Purchase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PurchaseRepositoryPort {
    Purchase save(Purchase purchase);
    Optional<Purchase> findById(UUID idPurchase);
    Optional<Purchase> findByTransactionId(String transactionId);
    List<Purchase> findByClientId(UUID idClient);
}
