package com.proyecto.ventas_online.application.ports.in.auth;

import com.proyecto.ventas_online.domain.model.User;

public interface LoginUseCase {
    User login(String email, String rawPassword);
}
