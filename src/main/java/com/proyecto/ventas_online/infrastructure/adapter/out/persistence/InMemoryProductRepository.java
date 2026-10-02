package com.proyecto.ventas_online.infrastructure.adapter.out.persistence;

import com.proyecto.ventas_online.application.ports.out.ProductRepositoryPort;
import com.proyecto.ventas_online.domain.model.Product;
import com.proyecto.ventas_online.infrastructure.mappers.ProductMapper;
import com.proyecto.ventas_online.infrastructure.model.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class InMemoryProductRepository implements ProductRepositoryPort {

    private final ProductMapper productMapper;
    private final Map<UUID, ProductEntity> repositoryProduct = new HashMap<>();

    @Override
    public Optional<Product> findProductById(UUID id) {
        return Optional.ofNullable(repositoryProduct.get(id))
                .map(productMapper::toDomain);
    }

    @Override
    public Product saveProduct(Product productBySave) {
        ProductEntity productEntityBySave = productMapper.toEntity(productBySave);
        repositoryProduct.put(productEntityBySave.getId(),productEntityBySave);
        return productBySave;
    }

    @Override
    public void deleteProduct(UUID id) {
        ProductEntity entityDeleted = repositoryProduct.remove(id);
        productMapper.toDomain(entityDeleted);
    }

    @Override
    public Product updateProduct(Product productByUpdate) {
        ProductEntity entityByUpdate = productMapper.toEntity(productByUpdate);
        repositoryProduct.put(productByUpdate.getId(), entityByUpdate);
        return productByUpdate;
    }

    @Override
    public List<Product> getAll() {
        return repositoryProduct
                .values().stream().map(productMapper::toDomain).toList();
    }
}
