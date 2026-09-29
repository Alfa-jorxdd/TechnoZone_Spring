package com.proyecto.ventas_online.application.ports.out;

import com.proyecto.ventas_online.application.dto.product.ProductSaveResponseDTO;
import com.proyecto.ventas_online.domain.model.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepositoryPort {
    Optional<Product> findProductById(UUID id);
    ProductSaveResponseDTO saveProduct(Product productBySave);
    void deleteProduct(UUID id);
    void updateProduct(Product productByUpdate);
}
