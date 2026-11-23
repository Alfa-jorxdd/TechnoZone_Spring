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
import com.proyecto.ventas_online.infrastructure.dto.purchase.CheckoutResponse;
import com.proyecto.ventas_online.application.service.payment.status.register.PaymentStatusUseCaseRegistry;
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
        ConfirmPurchaseUseCase {

    private final PurchaseRepositoryPort purchaseRepositoryPort;
    private final CartRepositoryPort cartRepositoryPort;
    private final SessionContext sessionContext;
    private final PaymentGatewayRegistry gatewayRegistry;
    private final PaymentStatusUseCaseRegistry paymentStatusUseCaseRegistry;
    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public CheckoutResultResponse checkout(PaymentInput input) {
        Purchase newPurchase = getCurrentPurchase(input);
        validateStock(newPurchase);
        PaymentResponse response = chargePurchase(input, newPurchase);
        PaymentStatusUseCase paymentStatusUseCase = paymentStatusUseCaseRegistry.getHandler(response.getStatus());
        paymentStatusUseCase.handle(newPurchase);

        return new CheckoutResultResponse(newPurchase, response.getApprovalUrl());
    }

    @Override
    public Purchase confirm(String transactionId) {
        Purchase purchase = getCurrentPurchase(transactionId);
        if (!purcharseIsPendingApproval(purchase.getStatus())) throw new IllegalStateException("The purchase is not pending approval");
        PaymentResponse response = confirmPurchase(purchase, transactionId);
        PaymentStatusUseCase paymentStatusUseCase = paymentStatusUseCaseRegistry.getHandler(response.getStatus());
        paymentStatusUseCase.handle(purchase);

        return purchase;
    }

    @Override
    public List<Purchase> getHistory() {
        return purchaseRepositoryPort.findByClientId(sessionContext.getCurrentUserId());
    }

    @Override
    public Purchase getById(UUID purchaseId) {
        return purchaseRepositoryPort.findById(purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundException(purchaseId));
    }

    private List<PurchaseItem> buildItems(Cart cart){
        return cart.getItems().stream()
                .map(cartItem -> {
                    Product product = productRepositoryPort.findProductById(cartItem.getIdProduct())
                            .orElseThrow(() -> new ProductNotFoundException(cartItem.getIdProduct()));
                    return new PurchaseItem(
                            product.getId(),
                            product.getName(),
                            product.getPrice(),
                            cartItem.getQuantity()
                    );
                }).toList();
    }

    private void validateStock(Purchase purchase) {
        loadAndValidateStock(purchase);
    }

    private Map<UUID, Product> loadAndValidateStock(Purchase purchase){
        Map<UUID, Product> productsToDiscount = new HashMap<>();
        purchase.getItems()
                .forEach(item -> {
                    Product product = productRepositoryPort.findProductById(item.idProduct())
                            .orElseThrow(() -> new ProductNotFoundException(item.idProduct()));
                    if (product.getStock() < item.quantity()) throw new InsufficientStockException();
                    productsToDiscount.put(product.getId(), product);
                });
        return productsToDiscount;
    }

    private Purchase getCurrentPurchase(PaymentInput input){
        UUID idClient = sessionContext.getCurrentUserId();
        Cart cart = cartRepositoryPort.findCartByUserId(idClient)
                .orElseThrow(CartNotFoundException::new);

        return new Purchase(
                idClient, buildItems(cart), cart.calculateTotal(), input.method());
    }

    private Purchase getCurrentPurchase(String transactionId){
        return purchaseRepositoryPort.findByTransactionId(transactionId)
                .orElseThrow(() -> new PurchaseNotFoundException(transactionId));
    }

    private PaymentResponse chargePurchase(PaymentInput input, Purchase purchase){
        PaymentResponse response = gatewayRegistry.charge(input, purchase.getTotal());
        purchase.attachTransaction(response.getTransactionId());
        return response;
    }

    private PaymentResponse confirmPurchase(Purchase purchase, String transactionId){
        return gatewayRegistry.confirm(purchase.getPaymentMethod(), transactionId);
    }

    private boolean purcharseIsPendingApproval(PaymentStatus status){
        return status == PaymentStatus.PENDING_APPROVAL;
    }
}
