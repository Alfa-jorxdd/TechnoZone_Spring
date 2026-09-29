package com.proyecto.ventas_online.application.ports.in.user;

import com.proyecto.ventas_online.domain.model.User;
import com.proyecto.ventas_online.application.dto.user.UpdateUserDTO;

public interface UpdateUserUseCase {
    User updateUser(UpdateUserDTO userDTO);
}
