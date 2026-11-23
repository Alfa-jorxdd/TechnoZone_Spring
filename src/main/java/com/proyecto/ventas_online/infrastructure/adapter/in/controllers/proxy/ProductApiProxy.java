package com.proyecto.ventas_online.infrastructure.adapter.in.controllers.proxy;

import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.api.ProductApi;
import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.proxy.helper.AccessGuard;
import com.proyecto.ventas_online.infrastructure.dto.product.CreateProductRequest;
import com.proyecto.ventas_online.infrastructure.dto.product.ProductResponse;
import com.proyecto.ventas_online.infrastructure.dto.product.UpdateProductRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductApiProxy implements ProductApi {

    private final ProductApi real;
    private final AccessGuard guard;

    @Override public ProductResponse create(CreateProductRequest request) {
        guard.requireAdmin();
        return real.create(request);
    }

    @Override public ProductResponse update(UpdateProductRequest request) {
        guard.requireAdmin();
        return real.update(request);
    }

    @Override public void delete(UUID id) {
        guard.requireAdmin();
        real.delete(id);
    }

    @Override public ProductResponse findById(UUID id) { return real.findById(id); }
    @Override public List<ProductResponse> findAll() { return real.findAll(); }
}
