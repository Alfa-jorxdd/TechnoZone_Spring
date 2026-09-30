package com.proyecto.ventas_online.application.ports.out;

import com.proyecto.ventas_online.application.dto.product.ProductSaveResponseDTO;
import com.proyecto.ventas_online.domain.model.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepositoryPort {
    Optional<Product> findProductById(UUID id);
    Product saveProduct(Product productBySave);
    Product deleteProduct(UUID id);
    Product updateProduct(Product productByUpdate);
}
