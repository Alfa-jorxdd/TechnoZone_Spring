package com.proyecto.ventas_online.application.ports.in.product;

import com.proyecto.ventas_online.domain.model.Product;

public interface CreateProductUseCase {
    Product createProduct(Product dto);
}
