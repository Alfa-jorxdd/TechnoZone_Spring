package com.proyecto.ventas_online.application.ports.out;

import com.proyecto.ventas_online.domain.model.Cart;

import java.util.Optional;
import java.util.UUID;

public interface CartRepositoryPort {
    Optional<Cart> findCartById(UUID id);
    Optional<Cart> findCartByUserId(UUID userId);
    void deleteCartByUserId(UUID id);
    Cart saveCart(Cart cartBySave);
}
