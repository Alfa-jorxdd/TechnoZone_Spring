package com.proyecto.ventas_online.infrastructure.mappers;

import com.proyecto.ventas_online.domain.model.Cart;
import com.proyecto.ventas_online.infrastructure.dto.cart.CartItemResponse;
import com.proyecto.ventas_online.infrastructure.dto.cart.CartResponse;
import com.proyecto.ventas_online.infrastructure.model.CartEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CartMapper {

    public Cart toDomain(CartEntity cartEntity){
        if (cartEntity == null){
            return null;
        }
        return new Cart.CartBuilder()
                .idCart(cartEntity.getIdCart())
                .idClient(cartEntity.getIdClient())
                .items(cartEntity.getItems())
                .buildExisting();
    }

    public CartEntity toEntity(Cart cart){
        if (cart == null) {
            return null;
        }
        return CartEntity.builder()
                .idCart(cart.getIdCart())
                .idClient(cart.getIdClient())
                .items(cart.getItems())
                .build();
    }

    public CartResponse toResponse(Cart cart) {
        List<CartItemResponse> items = cart.getItems().stream()
                .map(i -> new CartItemResponse(
                        i.getIdProduct(), i.getQuantity(), i.getUnitPrice(), i.subtotal()))
                .toList();

        BigDecimal total = items.stream()
                .map(CartItemResponse::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CartResponse(cart.getIdCart(), cart.getIdClient(), items, total);
    }
}
