package com.proyecto.ventas_online.application.ports.in.category;

import com.proyecto.ventas_online.domain.model.Category;

import java.util.List;

public interface GetAllCategoryUseCase {
    List<Category> getAllCategory();
}
