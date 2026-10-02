package com.proyecto.ventas_online.infrastructure.controllers.api;

import com.proyecto.ventas_online.infrastructure.dto.category.CategoryResponse;
import com.proyecto.ventas_online.infrastructure.dto.category.CreateCategoryRequest;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
public interface CategoryApi {
    CategoryResponse create(@Valid CreateCategoryRequest request);
    CategoryResponse findById(UUID id);
    List<CategoryResponse> findAll();
}