package com.proyecto.ventas_online.application.dto.user;

import com.proyecto.ventas_online.domain.model.Role;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserSaveResponseDTO {
    private final String name;
    private final String lastname;
    private final String email;
    private final Role role;
}
