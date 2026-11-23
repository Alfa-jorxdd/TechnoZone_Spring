package com.proyecto.ventas_online.application.service;

import com.proyecto.ventas_online.application.ports.in.product.*;
import com.proyecto.ventas_online.application.ports.out.CartRepositoryPort;
import com.proyecto.ventas_online.application.ports.out.CategoryRepositoryPort;
import com.proyecto.ventas_online.application.ports.out.DomainEventPublisher;
import com.proyecto.ventas_online.application.ports.out.ProductRepositoryPort;
import com.proyecto.ventas_online.domain.event.ProductDeletedEvent;
import com.proyecto.ventas_online.domain.event.ProductUpdatePriceEvent;
import com.proyecto.ventas_online.domain.exception.category.CategoryNotFoundException;
import com.proyecto.ventas_online.domain.exception.product.ProductNotFoundException;
import com.proyecto.ventas_online.domain.model.Cart;
import com.proyecto.ventas_online.domain.model.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductService implements
        CreateProductUseCase,
        DeleteProductUseCase,
        GetAllProductsUseCase,
        GetProductUseCase,
        UpdateProductUseCase {

    private final ProductRepositoryPort repositoryProduct;
    private final CategoryRepositoryPort repositoryCategory;
    private final DomainEventPublisher eventPublisher;

    @Override
    public Product createProduct(Product product) {
        if (repositoryCategory.findCategoryById(product.getIdCategory()).isEmpty()){
            throw new CategoryNotFoundException(product.getIdCategory());
        }
        return repositoryProduct.saveProduct(product);
    }

    @Override
    public void deteleProduct(UUID id) {
        if (repositoryProduct.findProductById(id).isEmpty()){
            throw new ProductNotFoundException(id);
        }
        repositoryProduct.deleteProduct(id);
        eventPublisher.publish(new ProductDeletedEvent(id));
    }

    @Override
    public Product updateProduct(Product productToUpdate) {
        if (repositoryCategory.findCategoryById(productToUpdate.getIdCategory()).isEmpty()){
            throw new CategoryNotFoundException(productToUpdate.getIdCategory());
        }
        Product updated = repositoryProduct.updateProduct(productToUpdate);
        Product productInMemory = repositoryProduct.findProductById(productToUpdate.getId())
                .orElseThrow(() -> new ProductNotFoundException(productToUpdate.getId()));

        if (productInMemory.getPrice().compareTo(productToUpdate.getPrice()) != 0){
            eventPublisher.publish(new ProductUpdatePriceEvent(
                    productToUpdate.getId(),
                    productToUpdate.getPrice(),
                    productToUpdate.getStock()));
        }

        return updated;
    }

    @Override
    public List<Product> getALl() {
        return repositoryProduct.getAll();
    }

    @Override
    public Product findProductById(UUID id) {
        return repositoryProduct.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
