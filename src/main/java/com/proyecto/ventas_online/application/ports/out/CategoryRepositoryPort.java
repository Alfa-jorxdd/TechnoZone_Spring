package com.proyecto.ventas_online.application.ports.out;

import com.proyecto.ventas_online.domain.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepositoryPort {
    Optional<Category> findCategoryById(UUID idCategory);
    Category saveCategory(Category category);
    List<Category> getAll();
}
