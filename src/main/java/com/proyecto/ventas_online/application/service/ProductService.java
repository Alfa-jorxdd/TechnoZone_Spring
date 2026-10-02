package com.proyecto.ventas_online.application.service;

import com.proyecto.ventas_online.application.ports.in.product.*;
import com.proyecto.ventas_online.application.ports.out.CategoryRepositoryPort;
import com.proyecto.ventas_online.application.ports.out.ProductRepositoryPort;
import com.proyecto.ventas_online.domain.exception.category.CategoryNotFoundException;
import com.proyecto.ventas_online.domain.exception.product.ProductNotFoundException;
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

    @Override
    public Product updateProduct(Product product) {
        if (repositoryCategory.findCategoryById(product.getIdCategory()).isEmpty()){
            throw new CategoryNotFoundException(product.getIdCategory());
        }
        return repositoryProduct.updateProduct(product);
    }
}
