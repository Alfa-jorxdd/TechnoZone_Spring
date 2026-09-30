package com.proyecto.ventas_online.application.dto.category;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Data
public class GetCategoryResponseDTO {
    private final UUID id;
    private final String name;
}
