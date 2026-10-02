package com.proyecto.ventas_online.infrastructure.controllers.api;

import com.proyecto.ventas_online.infrastructure.dto.auth.LoginRequest;
import com.proyecto.ventas_online.infrastructure.dto.auth.RegisterRequest;
import com.proyecto.ventas_online.infrastructure.dto.user.UserResponse;
import jakarta.validation.Valid;

public interface AuthApi {
    UserResponse login(@Valid LoginRequest request);
    void logout();
    UserResponse register(@Valid RegisterRequest request);
}
