package com.proyecto.ventas_online.application.service;

import com.proyecto.ventas_online.application.ports.in.payment.PaymentInput;
import com.proyecto.ventas_online.application.ports.in.payment.PaymentStatusUseCase;
import com.proyecto.ventas_online.application.ports.in.purchase.*;
import com.proyecto.ventas_online.application.ports.out.CartRepositoryPort;
import com.proyecto.ventas_online.application.ports.out.ProductRepositoryPort;
import com.proyecto.ventas_online.application.ports.out.SessionContext;
import com.proyecto.ventas_online.application.ports.out.PurchaseRepositoryPort;
import com.proyecto.ventas_online.application.ports.out.payment.PaymentResponse;
import com.proyecto.ventas_online.application.service.payment.PaymentGatewayRegistry;
import com.proyecto.ventas_online.application.service.payment.state.PurchaseContext;
import com.proyecto.ventas_online.application.service.payment.state.PurchaseContextFactory;
import com.proyecto.ventas_online.domain.exception.purchase.PurchaseNotPendingException;
import com.proyecto.ventas_online.infrastructure.dto.purchase.CheckoutResponse;
import com.proyecto.ventas_online.domain.exception.cart.CartNotFoundException;
import com.proyecto.ventas_online.domain.exception.product.InsufficientStockException;
import com.proyecto.ventas_online.domain.exception.product.ProductNotFoundException;
import com.proyecto.ventas_online.domain.exception.purchase.PurchaseNotFoundException;
import com.proyecto.ventas_online.domain.model.*;
import com.proyecto.ventas_online.infrastructure.dto.purchase.CheckoutResultResponse;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class PurchaseService implements
        CheckoutUseCase,
        GetPurchaseUseCase,
        GetPurchaseHistoryUseCase,
        ConfirmPurchaseUseCase,
        CancelPurchaseUseCase{

    private final PurchaseRepositoryPort purchaseRepositoryPort;
    private final CartRepositoryPort cartRepositoryPort;
    private final SessionContext sessionContext;
    private final PaymentGatewayRegistry gatewayRegistry;
    private final ProductRepositoryPort productRepositoryPort;
    private final PurchaseContextFactory contextFactory;

    @Override
    public CheckoutResponse checkout(PaymentInput input) {
        Purchase newPurchase = getCurrentPurchase(input);
        validateStock(newPurchase);

        PaymentResponse response = chargePurchase(input, newPurchase);
        purchaseRepositoryPort.save(newPurchase);

        dispatch(contextFactory.of(newPurchase), response);

        return new CheckoutResponse(newPurchase, response.getApprovalUrl());
    }

    @Override
    public Purchase confirm(String transactionId) {
        Purchase purchase = getOwnedByTransaction(transactionId);
        PurchaseContext ctx = contextFactory.of(purchase);

        if (!ctx.canConfirm()) throw new PurchaseNotPendingException();

        PaymentResponse response = confirmPurchase(purchase, transactionId);
        dispatch(ctx, response);
        return purchase;
    }

    @Override
    public Purchase cancel(UUID idPurchase) {
        Purchase purchase = getOwnedById(idPurchase);
        contextFactory.of(purchase).cancel();
        return purchase;
    }

    @Override
    public List<Purchase> getHistory() {
        return purchaseRepositoryPort.findByClientId(sessionContext.getCurrentUserId());
    }

    @Override
    public Purchase getById(UUID purchaseId) {
        return getOwnedById(purchaseId);
    }

    private List<PurchaseItem> buildItems(Cart cart){
        return cart.getItems().stream()
                .map(cartItem -> {
                    Product product = productRepositoryPort.findProductById(cartItem.getIdProduct())
                            .orElseThrow(() -> new ProductNotFoundException(cartItem.getIdProduct()));
                    return new PurchaseItem(
                            product.getId(),
                            product.getName(),
                            cartItem.getUnitPrice(),
                            cartItem.getQuantity()
                    );
                }).toList();
    }

    private void validateStock(Purchase purchase) {
        for (PurchaseItem item : purchase.getItems()) {
            Product product = productRepositoryPort.findProductById(item.idProduct())
                    .orElseThrow(() -> new ProductNotFoundException(item.idProduct()));
            if (product.getStock() < item.quantity()) throw new InsufficientStockException();
        }
    }

    private Purchase getCurrentPurchase(PaymentInput input){
        UUID idClient = sessionContext.getCurrentUserId();
        Cart cart = cartRepositoryPort.findCartByUserId(idClient)
                .orElseThrow(CartNotFoundException::new);

        return new Purchase(
                idClient, buildItems(cart), cart.calculateTotal(), input.method());
    }

    private PaymentResponse chargePurchase(PaymentInput input, Purchase purchase){
        PaymentResponse response = gatewayRegistry.charge(input, purchase.getTotal());
        purchase.attachTransaction(response.getTransactionId());
        return response;
    }

    private PaymentResponse confirmPurchase(Purchase purchase, String transactionId){
        return gatewayRegistry.confirm(purchase.getPaymentMethod(), transactionId);
    }

    private void dispatch(PurchaseContext ctx, PaymentResponse response) {
        switch (response.getStatus()) {
            case COMPLETED -> ctx.complete();
            case FAILED -> ctx.fail(response.getMessage());
            default -> { }
        }
    }

    private Purchase getOwnedByTransaction(String transactionId) {
        Purchase purchase = purchaseRepositoryPort.findByTransactionId(transactionId)
                .orElseThrow(() -> new PurchaseNotFoundException(transactionId));
        if (!purchase.getIdClient().equals(sessionContext.getCurrentUserId())) {
            throw new PurchaseNotFoundException(transactionId);
        }
        return purchase;
    }

    private Purchase getOwnedById(UUID idPurchase) {
        Purchase purchase = purchaseRepositoryPort.findById(idPurchase)
                .orElseThrow(() -> new PurchaseNotFoundException(idPurchase));
        if (!purchase.getIdClient().equals(sessionContext.getCurrentUserId())) {
            throw new PurchaseNotFoundException(idPurchase);
        }
        return purchase;
    }
}
