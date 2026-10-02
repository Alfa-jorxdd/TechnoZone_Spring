package com.proyecto.ventas_online.infrastructure.adapter.out.persistence;

import com.proyecto.ventas_online.application.ports.out.CartRepositoryPort;
import com.proyecto.ventas_online.domain.model.Cart;
import com.proyecto.ventas_online.infrastructure.mappers.CartMapper;
import com.proyecto.ventas_online.infrastructure.model.CartEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class InMemoryCartRepository implements CartRepositoryPort {

    private final CartMapper cartMapper;
    private final Map<UUID, CartEntity> repositoryCart = new HashMap<>();

    @Override
    public Optional<Cart> findCartById(UUID id) {
        return Optional.ofNullable(repositoryCart.get(id))
                .map(cartMapper::toDomain);
    }

    @Override
    public Optional<Cart> findCartByUserId(UUID userId) {
        return repositoryCart.values().stream()
                .filter(cartEntity -> cartEntity.getIdClient().equals(userId))
                .findFirst().map(cartMapper::toDomain);
    }

    @Override
    public void deleteCartByUserId(UUID id) {
        repositoryCart.values()
                .removeIf(cartEntity -> cartEntity.getIdClient().equals(id));
    }

    @Override
    public Cart saveCart(Cart cartToSave) {
        CartEntity cartSaved = cartMapper.toEntity(cartToSave);
        repositoryCart.put(cartSaved.getIdCart(), cartSaved);
        return cartToSave;
    }
}
