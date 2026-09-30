package com.proyecto.ventas_online.infrastructure.model;

import com.proyecto.ventas_online.domain.model.CartItem;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@ToString
@Builder
public class CartEntity {
    private final UUID idCart;
    private final UUID idClient;
    private final List<CartItem> items;
}
