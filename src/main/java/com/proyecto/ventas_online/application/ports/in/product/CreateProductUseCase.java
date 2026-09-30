package com.proyecto.ventas_online.application.ports.in.product;

import com.proyecto.ventas_online.application.dto.product.ProductSaveResponseDTO;
import com.proyecto.ventas_online.domain.model.Product;
import com.proyecto.ventas_online.application.dto.product.CreateProductDTO;

public interface CreateProductUseCase {
    ProductSaveResponseDTO createProduct(CreateProductDTO dto);
}
