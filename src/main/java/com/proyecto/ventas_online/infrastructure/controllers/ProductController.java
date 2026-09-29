package com.proyecto.ventas_online.infrastructure.controllers;

import com.proyecto.ventas_online.application.dto.product.CreateProductDTO;
import com.proyecto.ventas_online.application.ports.in.cart.CreateCartUseCase;
import com.proyecto.ventas_online.application.ports.in.product.CreateProductUseCase;
import com.proyecto.ventas_online.application.ports.in.product.DeleteProductUseCase;
import com.proyecto.ventas_online.domain.model.Product;

import java.util.UUID;

public class ProductController implements CreateProductUseCase, DeleteProductUseCase {
    @Override
    public Product createProduct(CreateProductDTO dto) {
        return null;
    }

    @Override
    public void deteleProduct(UUID id) {

    }
}
