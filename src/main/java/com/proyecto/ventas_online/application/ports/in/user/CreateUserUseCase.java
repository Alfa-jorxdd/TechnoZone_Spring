package com.proyecto.ventas_online.application.ports.in.user;

import com.proyecto.ventas_online.application.dto.user.UserSaveResponseDTO;
import com.proyecto.ventas_online.application.dto.user.CreateUserDTO;

public interface CreateUserUseCase {
    UserSaveResponseDTO createUser(CreateUserDTO userDTO);
}
