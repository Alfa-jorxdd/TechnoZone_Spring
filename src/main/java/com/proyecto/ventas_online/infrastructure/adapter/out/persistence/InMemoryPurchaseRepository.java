package com.proyecto.ventas_online.infrastructure.adapter.out.persistence;

import com.proyecto.ventas_online.application.ports.out.PurchaseRepositoryPort;
import com.proyecto.ventas_online.domain.model.Purchase;
import com.proyecto.ventas_online.infrastructure.mappers.PurchaseMapper;
import com.proyecto.ventas_online.infrastructure.model.PurchaseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryPurchaseRepository implements PurchaseRepositoryPort {
    private final Map<UUID, PurchaseEntity> purchases = new HashMap<>();
    private final PurchaseMapper mapper;

    public InMemoryPurchaseRepository(PurchaseMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Purchase save(Purchase purchase) {
        purchases.put(purchase.getIdPurchase(), mapper.toEntity(purchase));
        return purchase;
    }

    @Override
    public Optional<Purchase> findById(UUID idPurchase) {
        return Optional.ofNullable(purchases.get(idPurchase)).map(mapper::toDomain);
    }

    @Override
    public Optional<Purchase> findByTransactionId(String transactionId) {
        return purchases.values().stream()
                .filter(e -> e.getTransactionId().equals(transactionId))
                .findFirst()
                .map(mapper::toDomain);
    }

    @Override
    public List<Purchase> findByClientId(UUID idClient) {
        return purchases.values().stream()
                .filter(e -> e.getIdClient().equals(idClient))
                .map(mapper::toDomain)
                .sorted(Comparator.comparing(Purchase::getCreatedAt).reversed())
                .toList();
    }

}
