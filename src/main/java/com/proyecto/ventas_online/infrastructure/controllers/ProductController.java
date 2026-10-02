package com.proyecto.ventas_online.infrastructure.controllers;

import com.proyecto.ventas_online.application.ports.in.product.*;
import com.proyecto.ventas_online.domain.model.Product;
import com.proyecto.ventas_online.infrastructure.controllers.api.ProductApi;
import com.proyecto.ventas_online.infrastructure.dto.product.CreateProductRequest;
import com.proyecto.ventas_online.infrastructure.dto.product.ProductResponse;
import com.proyecto.ventas_online.infrastructure.dto.product.UpdateProductRequest;
import com.proyecto.ventas_online.infrastructure.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Component
@Validated
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductMapper productMapper;

    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final GetProductUseCase getProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    @Override
    public ProductResponse create(CreateProductRequest request) {
        Product productSaved = createProductUseCase
                .createProduct(productMapper.toNewDomain(request));
        return productMapper.toResponse(productSaved);
    }

    @Override
    public ProductResponse update(UpdateProductRequest request) {
        Product productUpdated = updateProductUseCase
                .updateProduct(productMapper.toExistingDomain(request));
        return productMapper.toResponse(productUpdated);
    }

    @Override
    public ProductResponse findById(UUID id) {
        return productMapper.toResponse(getProductUseCase.findProductById(id));
    }

    @Override
    public List<ProductResponse> findAll() {
        return getAllProductsUseCase.getALl()
                .stream().map(productMapper::toResponse).toList();
    }

    @Override
    public void delete(UUID id) {
        deleteProductUseCase.deteleProduct(id);
    }
}
