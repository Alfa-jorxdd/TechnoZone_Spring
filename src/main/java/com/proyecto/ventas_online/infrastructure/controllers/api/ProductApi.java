package com.proyecto.ventas_online.infrastructure.controllers.api;

import com.proyecto.ventas_online.infrastructure.dto.product.CreateProductRequest;
import com.proyecto.ventas_online.infrastructure.dto.product.ProductResponse;
import com.proyecto.ventas_online.infrastructure.dto.product.UpdateProductRequest;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
public interface ProductApi {
    ProductResponse create(@Valid CreateProductRequest request);
    ProductResponse update(@Valid UpdateProductRequest request);
    ProductResponse findById(UUID id);
    List<ProductResponse> findAll();
    void delete(UUID id);
}
