package com.proyecto.ventas_online.application.ports.in.user;

import java.util.UUID;

public interface DeleteUserUseCase {
    void deleteUser(UUID id);
}
