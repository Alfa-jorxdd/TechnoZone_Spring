package com.proyecto.ventas_online.infrastructure.controllers.api;

import com.proyecto.ventas_online.infrastructure.dto.cart.AddProductToCartRequest;
import com.proyecto.ventas_online.infrastructure.dto.cart.CartResponse;
import com.proyecto.ventas_online.infrastructure.dto.cart.DecreaseProductRequest;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public interface CartApi {
    CartResponse addProduct(@Valid AddProductToCartRequest request);
    CartResponse decreaseProduct(@Valid DecreaseProductRequest request);
    CartResponse removeProduct(UUID idProduct);
    CartResponse clear();
    CartResponse getMyCart();
    CartResponse findById(UUID idCart);
    CartResponse findByUserId();
    void deleteByUser();
}
