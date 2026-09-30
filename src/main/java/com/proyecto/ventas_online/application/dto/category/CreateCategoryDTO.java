package com.proyecto.ventas_online.application.dto.category;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CreateCategoryDTO {
    private final String name;
}
