package com.proyecto.ventas_online.application.ports.in.product;

import com.proyecto.ventas_online.domain.model.Product;

import java.util.UUID;

public interface DeleteProductUseCase {
    void deteleProduct(UUID id);
}
