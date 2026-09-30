package com.proyecto.ventas_online.application.ports.in.category;

import com.proyecto.ventas_online.application.dto.category.CategorySaveResponseDTO;
import com.proyecto.ventas_online.application.dto.category.CreateCategoryDTO;

public interface CreateCategoryUseCase {
    CategorySaveResponseDTO createCategory(CreateCategoryDTO categoryDTO);
}
