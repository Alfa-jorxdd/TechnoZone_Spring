package com.proyecto.ventas_online.infrastructure.controllers;

import com.proyecto.ventas_online.application.ports.in.category.CreateCategoryUseCase;
import com.proyecto.ventas_online.application.ports.in.category.GetAllCategoryUseCase;
import com.proyecto.ventas_online.application.ports.in.category.GetCategoryUseCase;
import com.proyecto.ventas_online.domain.model.Category;
import com.proyecto.ventas_online.infrastructure.controllers.api.CategoryApi;
import com.proyecto.ventas_online.infrastructure.dto.category.CategoryResponse;
import com.proyecto.ventas_online.infrastructure.dto.category.CreateCategoryRequest;
import com.proyecto.ventas_online.infrastructure.mappers.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Component
@Validated
@RequiredArgsConstructor
public class CategoryController implements CategoryApi {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetAllCategoryUseCase getAllCategoryUseCase;
    private final GetCategoryUseCase getCategoryUseCase;

    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse create(CreateCategoryRequest request) {
        Category categoryToSave = createCategoryUseCase
                .createCategory(categoryMapper.toNewDomain(request));
        return categoryMapper.toResponse(categoryToSave);
    }

    @Override
    public CategoryResponse findById(UUID id) {
        return categoryMapper
                .toResponse(getCategoryUseCase.findCategoryById(id));
    }

    @Override
    public List<CategoryResponse> findAll() {
        return getAllCategoryUseCase.getAllCategory().stream()
                .map(categoryMapper::toResponse).toList();
    }
}
