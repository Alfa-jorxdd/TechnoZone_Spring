package com.proyecto.ventas_online.application.ports.in.product;

import com.proyecto.ventas_online.domain.model.Product;
import com.proyecto.ventas_online.application.dto.product.UpdateProductDTO;

public interface UpdateProductUseCase {
    Product updateProduct(UpdateProductDTO dto);
}
