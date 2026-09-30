package com.proyecto.ventas_online.application.dto.product;

import com.proyecto.ventas_online.domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Data
public class UpdateProductDTO {
    //aquí hay que pensar en como actualizar el user. Puede ser con un builder
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
    private String imagePath;
}
