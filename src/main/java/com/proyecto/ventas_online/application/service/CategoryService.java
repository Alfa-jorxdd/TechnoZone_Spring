package com.proyecto.ventas_online.application.service;

import com.proyecto.ventas_online.application.ports.in.category.CreateCategoryUseCase;
import com.proyecto.ventas_online.application.ports.in.category.GetAllCategoryUseCase;
import com.proyecto.ventas_online.application.ports.in.category.GetCategoryUseCase;
import com.proyecto.ventas_online.application.ports.out.CategoryRepositoryPort;
import com.proyecto.ventas_online.domain.exception.category.CategoryNotFoundException;
import com.proyecto.ventas_online.domain.exception.category.DuplicateCategoryException;
import com.proyecto.ventas_online.domain.model.Category;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class CategoryService implements
        CreateCategoryUseCase,
        GetAllCategoryUseCase,
        GetCategoryUseCase {

    private final CategoryRepositoryPort categoryRepository;

    @Override
    public Category createCategory(Category categoryToSave) {
        Optional<Category> categoryAux = getAllCategory().stream()
                .filter(
                        categoryInMemory -> categoryInMemory.getName().toLowerCase()
                                .equalsIgnoreCase(categoryToSave.getName().toLowerCase()))
                .findFirst();

        if (categoryAux.isPresent()){
            throw new DuplicateCategoryException(categoryToSave.getName());
        }
        return categoryRepository.saveCategory(categoryToSave);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.getAll();
    }

    @Override
    public Category findCategoryById(UUID idCategory) {
        return categoryRepository.findCategoryById(idCategory)
                .orElseThrow(() -> new CategoryNotFoundException(idCategory));
    }
}
