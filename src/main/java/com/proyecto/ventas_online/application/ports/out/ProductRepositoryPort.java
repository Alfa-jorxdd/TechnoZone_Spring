package com.proyecto.ventas_online.application.ports.out;

import com.proyecto.ventas_online.domain.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepositoryPort {
    Optional<Product> findProductById(UUID id);
    Product saveProduct(Product productBySave);
    void deleteProduct(UUID id);
    Product updateProduct(Product productByUpdate);
    List<Product> getAll();
}
