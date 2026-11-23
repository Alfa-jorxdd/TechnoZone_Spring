package com.proyecto.ventas_online.infrastructure.mappers;

import com.proyecto.ventas_online.infrastructure.dto.purchase.CheckoutResponse;
import com.proyecto.ventas_online.domain.model.Purchase;
import com.proyecto.ventas_online.domain.model.PurchaseItem;
import com.proyecto.ventas_online.infrastructure.dto.purchase.CheckoutResultResponse;
import com.proyecto.ventas_online.infrastructure.dto.purchase.PurchaseItemResponse;
import com.proyecto.ventas_online.infrastructure.dto.purchase.PurchaseResponse;
import com.proyecto.ventas_online.infrastructure.model.PurchaseEntity;
import com.proyecto.ventas_online.infrastructure.model.PurchaseItemEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PurchaseMapper {

    public PurchaseEntity toEntity(Purchase purchase) {
        return PurchaseEntity.builder()
                .idPurchase(purchase.getIdPurchase())
                .idClient(purchase.getIdClient())
                .items(purchase.getItems().stream().map(this::toItemEntity).toList())
                .total(purchase.getTotal())
                .paymentMethod(purchase.getPaymentMethod())
                .createdAt(purchase.getCreatedAt())
                .status(purchase.getStatus())
                .transactionId(purchase.getTransactionId())
                .build();
    }

    public PurchaseItemEntity toItemEntity(PurchaseItem item) {
        return PurchaseItemEntity.builder()
                .idProduct(item.idProduct())
                .productName(item.productName())
                .unitPrice(item.unitPrice())
                .quantity(item.quantity())
                .build();
    }

    public Purchase toDomain(PurchaseEntity entity) {
        return Purchase.restore(
                entity.getIdPurchase(),
                entity.getIdClient(),
                entity.getItems().stream().map(this::toItemDomain).toList(),
                entity.getTotal(),
                entity.getPaymentMethod(),
                entity.getCreatedAt(),
                entity.getStatus(),
                entity.getTransactionId()
        );
    }

    public PurchaseItem toItemDomain(PurchaseItemEntity entity) {
        return new PurchaseItem(
                entity.getIdProduct(),
                entity.getProductName(),
                entity.getUnitPrice(),
                entity.getQuantity()
        );
    }

    public PurchaseResponse toResponse(Purchase purchase) {
        return new PurchaseResponse(
                purchase.getIdPurchase(),
                purchase.getTransactionId(),
                purchase.getCreatedAt(),
                purchase.getPaymentMethod(),
                purchase.getStatus(),
                purchase.getTotal(),
                purchase.getItems().stream().map(this::toItemResponse).toList());
    }

    public List<PurchaseResponse> toResponse(List<Purchase> purchases) {
        return purchases.stream().map(this::toResponse).toList();
    }

    public CheckoutResultResponse toResponse(CheckoutResponse checkout) {
        return new CheckoutResultResponse(toResponse(checkout.purchase()), checkout.approvalUrl());
    }

    private PurchaseItemResponse toItemResponse(PurchaseItem item) {
        return new PurchaseItemResponse(
                item.idProduct(), item.productName(), item.unitPrice(), item.quantity(),
                item.unitPrice().multiply(BigDecimal.valueOf(item.quantity())));
    }
}
