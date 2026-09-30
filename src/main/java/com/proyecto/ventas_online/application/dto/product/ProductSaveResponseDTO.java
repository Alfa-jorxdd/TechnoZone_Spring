package com.proyecto.ventas_online.application.dto.product;

import com.proyecto.ventas_online.domain.model.Category;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Data
public class ProductSaveResponseDTO {
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Category category;
    private final String imagePath;
}
