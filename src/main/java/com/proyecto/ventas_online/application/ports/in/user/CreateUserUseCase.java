package com.proyecto.ventas_online.application.ports.in.user;

import com.proyecto.ventas_online.domain.model.User;
import com.proyecto.ventas_online.infrastructure.dto.user.CreateUserCommand;

public interface CreateUserUseCase {
    User createUser(CreateUserCommand command);
}
