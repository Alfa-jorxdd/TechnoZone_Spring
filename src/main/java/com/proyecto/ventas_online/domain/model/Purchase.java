package com.proyecto.ventas_online.domain.model;

import com.proyecto.ventas_online.domain.exception.cart.EmptyCartException;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
public class Purchase {
    private final UUID idPurchase;
    private final UUID idClient;
    private final List<PurchaseItem> items;
    private final BigDecimal total;
    private final PaymentMethod paymentMethod;
    private final LocalDateTime createdAt;

    private PaymentStatus status;
    private String transactionId;

    public Purchase(UUID idClient, List<PurchaseItem> items, BigDecimal total, PaymentMethod paymentMethod) {
        if (items == null || items.isEmpty()) throw new EmptyCartException();
        this.idPurchase = UUID.randomUUID();
        this.idClient = idClient;
        this.items = List.copyOf(items);
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.createdAt = LocalDateTime.now();
        this.status = PaymentStatus.PENDING_APPROVAL;
    }

    private Purchase(UUID idPurchase, UUID idClient, List<PurchaseItem> items, BigDecimal total,
                     PaymentMethod paymentMethod, LocalDateTime createdAt,
                     PaymentStatus status, String transactionId) {
        this.idPurchase = idPurchase;
        this.idClient = idClient;
        this.items = List.copyOf(items);
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
        this.status = status;
        this.transactionId = transactionId;
    }

    public static Purchase restore(UUID idPurchase, UUID idClient, List<PurchaseItem> items,
                                   BigDecimal total, PaymentMethod paymentMethod,
                                   LocalDateTime createdAt, PaymentStatus status,
                                   String transactionId) {
        return new Purchase(idPurchase, idClient, items, total, paymentMethod,
                createdAt, status, transactionId);
    }

    public void attachTransaction(String transactionId) {
        this.transactionId = transactionId;
    }

    public void changeStatus(PaymentStatus newStatus) {
        this.status = newStatus;
    }
}
