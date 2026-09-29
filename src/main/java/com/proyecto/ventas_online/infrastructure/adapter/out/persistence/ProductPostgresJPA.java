package com.proyecto.ventas_online.infrastructure.adapter.out.persistence;

import com.proyecto.ventas_online.application.dto.product.ProductSaveResponseDTO;
import com.proyecto.ventas_online.application.ports.out.ProductRepositoryPort;
import com.proyecto.ventas_online.domain.model.Product;

import java.util.Optional;
import java.util.UUID;

public class ProductPostgresJPA implements ProductRepositoryPort {

    @Override
    public Optional<Product> findProductById(UUID id) {
        return Optional.empty();
    }

    @Override
    public ProductSaveResponseDTO saveProduct(Product productBySave) {
        return null;
    }

    @Override
    public void deleteProduct(UUID id) {

    }

    @Override
    public void updateProduct(Product productByUpdate) {

    }
}
