package com.proyecto.ventas_online.application.service;

import com.proyecto.ventas_online.application.event.DomainEventListener;
import com.proyecto.ventas_online.application.ports.out.CartRepositoryPort;
import com.proyecto.ventas_online.domain.event.ProductDeletedEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartProductDeletedListener implements DomainEventListener<ProductDeletedEvent> {

    private final CartRepositoryPort cartRepository;

    @Override public Class<ProductDeletedEvent> eventType() { return ProductDeletedEvent.class; }

    @Override
    public void onEvent(ProductDeletedEvent event) {
        cartRepository.findAll().stream()
                .filter(cart -> cart.getItems().stream()
                        .anyMatch(item -> item.getIdProduct().equals(event.idProduct())))
                .forEach(cart -> {
                    cart.getItems().removeIf(item -> item.getIdProduct().equals(event.idProduct()));
                    cartRepository.saveCart(cart);
                });
    }
}
