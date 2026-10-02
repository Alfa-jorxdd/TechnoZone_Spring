package com.proyecto.ventas_online.application.ports.in.cart;

import com.proyecto.ventas_online.domain.model.Cart;

import java.util.UUID;

public interface DecreaseProductQuantityUseCase {
    Cart decreaseProduct(UUID idProduct, int quantity);
}
