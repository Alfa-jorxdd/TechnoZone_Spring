package com.proyecto.ventas_online.application.service;

import com.proyecto.ventas_online.application.event.DomainEventListener;
import com.proyecto.ventas_online.application.ports.out.CartRepositoryPort;
import com.proyecto.ventas_online.domain.event.ProductUpdatePriceEvent;
import com.proyecto.ventas_online.domain.model.Cart;
import com.proyecto.ventas_online.domain.model.CartItem;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartProductUpdatedListener implements DomainEventListener<ProductUpdatePriceEvent> {

    private final CartRepositoryPort cartRepository;

    @Override public Class<ProductUpdatePriceEvent> eventType() { return ProductUpdatePriceEvent.class; }

    @Override
    public void onEvent(ProductUpdatePriceEvent event) {
        cartRepository.findAll().forEach(cart ->
                cart.getItems().stream()
                        .filter(item -> item.getIdProduct().equals(event.idProduct()))
                        .findFirst()
                        .ifPresent(item -> {
                            if (sync(cart, item, event)) cartRepository.saveCart(cart);
                        }));
    }

    private boolean sync(Cart cart, CartItem item, ProductUpdatePriceEvent event) {
        boolean changed = false;

        if (item.getUnitPrice().compareTo(event.price()) != 0) {
            item.setUnitPrice(event.price());
            changed = true;
        }
        int excess = item.getQuantity() - event.stock();
        if (excess > 0) {
            cart.decreaseProduct(event.idProduct(), excess);
            changed = true;
        }
        return changed;
    }
}
