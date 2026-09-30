package com.proyecto.ventas_online.application.ports.in.product;

import com.proyecto.ventas_online.application.dto.user.GetUserResponseDTO;

import java.util.UUID;

public interface GetProductUseCase {
    GetUserResponseDTO findProductById(UUID id);
}
