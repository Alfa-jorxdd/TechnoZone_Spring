package com.proyecto.ventas_online.infrastructure.controllers;

import com.proyecto.ventas_online.application.ports.in.cart.*;
import com.proyecto.ventas_online.infrastructure.controllers.api.CartApi;
import com.proyecto.ventas_online.infrastructure.dto.cart.AddProductToCartRequest;
import com.proyecto.ventas_online.infrastructure.dto.cart.CartResponse;
import com.proyecto.ventas_online.infrastructure.dto.cart.DecreaseProductRequest;
import com.proyecto.ventas_online.infrastructure.mappers.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Component
@Validated
@RequiredArgsConstructor
public class CartController implements CartApi {

    private final CartMapper cartMapper;

    private final AddProductUseCase addProductUseCase;
    private final DecreaseProductQuantityUseCase decreaseProductUseCase;
    private final DeleteItemCartUseCase deleteItemCartUseCase;
    private final ClearCartUseCase clearCartUseCase;
    private final GetCartByUserUseCase getCartByUserUseCase;
    private final GetCartUseCase getCartUseCase;
    private final DeleteCartByUserUseCase deleteCartByUserUseCase;

    @Override
    public CartResponse addProduct(AddProductToCartRequest request) {
        return cartMapper.toResponse(
                addProductUseCase.addProduct(request.idProduct(), request.quantity()));
    }

    @Override
    public CartResponse decreaseProduct(DecreaseProductRequest request) {
        return cartMapper.toResponse(
                decreaseProductUseCase.decreaseProduct(request.idProduct(), request.quantity()));
    }

    @Override
    public CartResponse removeProduct(UUID idProduct) {
        return cartMapper.toResponse(deleteItemCartUseCase.deleteItemCart(idProduct));
    }

    @Override
    public CartResponse clear() {
        return cartMapper.toResponse(clearCartUseCase.clearCart());
    }

    @Override
    public CartResponse getMyCart() {
        return cartMapper.toResponse(
                getCartByUserUseCase.findCartByUserId());
    }

    @Override
    public CartResponse findById(UUID idCart) {
        return cartMapper.toResponse(getCartUseCase.findById(idCart));
    }

    @Override
    public void deleteByUser() {
        deleteCartByUserUseCase.deleteCartByUserId();
    }
}
