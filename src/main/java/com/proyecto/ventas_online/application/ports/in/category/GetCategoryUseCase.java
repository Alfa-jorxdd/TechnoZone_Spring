package com.proyecto.ventas_online.application.ports.in.category;

import com.proyecto.ventas_online.application.dto.category.GetCategoryResponseDTO;

import java.util.UUID;

public interface GetCategoryUseCase {
    GetCategoryResponseDTO findCategoryById(UUID idCategory);
}
