package com.proyecto.ventas_online.application.dto.product;

import com.proyecto.ventas_online.domain.model.Category;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class CreateProductDTO {
    private final String name;
    private final BigDecimal price;
    private final Integer stock;
    private final String imagePath;
    private final Category category;
}
