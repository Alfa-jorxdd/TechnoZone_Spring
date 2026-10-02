package com.proyecto.ventas_online.application.ports.in.category;

import com.proyecto.ventas_online.domain.model.Category;

public interface CreateCategoryUseCase {
    Category createCategory(Category categoryDTO);
}
