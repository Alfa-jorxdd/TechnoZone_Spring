package com.proyecto.ventas_online.application.dto.user;

import com.proyecto.ventas_online.domain.model.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Data
public class GetUserResponseDTO {
    private final UUID id;
    private final String name;
    private final String lastname;
    private final String email;
    private final Role role;
}
