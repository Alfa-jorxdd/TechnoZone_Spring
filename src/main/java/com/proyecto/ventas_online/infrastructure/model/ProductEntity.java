package com.proyecto.ventas_online.infrastructure.model;

import com.proyecto.ventas_online.domain.model.Category;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductEntity {
    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Category category;
    private final String imagePath;
    private final Integer stock;

    public ProductEntity(String name, String description, BigDecimal price, Category category, String imagePath, Integer stock) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imagePath = imagePath;
        this.stock = stock;
    }
}
