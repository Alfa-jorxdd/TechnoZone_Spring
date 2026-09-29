package com.proyecto.ventas_online.application.dto.product;

import com.proyecto.ventas_online.domain.model.Category;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class UpdateProductDTO {
    //aquí hay que pensar en como actualizar el user. Puede ser con un builder
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
    private String imagePath;
}
