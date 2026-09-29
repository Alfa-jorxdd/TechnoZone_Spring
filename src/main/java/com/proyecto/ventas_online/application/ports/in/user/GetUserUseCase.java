package com.proyecto.ventas_online.application.ports.in.user;

import com.proyecto.ventas_online.domain.model.User;

import java.util.UUID;

public interface GetUserUseCase {
    User findUserById(UUID id);
}
