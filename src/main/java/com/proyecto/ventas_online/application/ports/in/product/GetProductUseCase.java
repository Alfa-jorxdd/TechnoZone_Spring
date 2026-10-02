package com.proyecto.ventas_online.application.ports.in.product;

import com.proyecto.ventas_online.domain.model.Product;

import java.util.UUID;

public interface GetProductUseCase {
    Product findProductById(UUID id);
}
