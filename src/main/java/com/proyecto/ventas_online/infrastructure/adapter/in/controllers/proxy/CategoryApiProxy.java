package com.proyecto.ventas_online.infrastructure.adapter.in.controllers.proxy;

import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.api.CategoryApi;
import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.proxy.helper.AccessGuard;
import com.proyecto.ventas_online.infrastructure.dto.category.CategoryResponse;
import com.proyecto.ventas_online.infrastructure.dto.category.CreateCategoryRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CategoryApiProxy implements CategoryApi {
    private final AccessGuard accessGuard;
    private final CategoryApi real;

    @Override
    public CategoryResponse create(CreateCategoryRequest request) {
        accessGuard.requireAdmin();
        return real.create(request);
    }

    @Override
    public CategoryResponse findById(UUID id) {
        return real.findById(id);
    }

    @Override
    public List<CategoryResponse> findAll() {
        return real.findAll();
    }
}
