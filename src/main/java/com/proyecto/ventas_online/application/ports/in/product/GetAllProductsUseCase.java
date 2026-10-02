package com.proyecto.ventas_online.application.ports.in.product;

import com.proyecto.ventas_online.domain.model.Product;

import java.util.List;

public interface GetAllProductsUseCase {
    List<Product> getALl();
}
