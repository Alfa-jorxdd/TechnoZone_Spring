package com.proyecto.ventas_online.infrastructure.controllers.api;

import com.proyecto.ventas_online.infrastructure.dto.auth.RegisterRequest;
import com.proyecto.ventas_online.infrastructure.dto.user.CreateUserRequest;
import com.proyecto.ventas_online.infrastructure.dto.user.UpdateUserRequest;
import com.proyecto.ventas_online.infrastructure.dto.user.UserResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
public interface UserApi {
    UserResponse create(@Valid CreateUserRequest request);
    UserResponse update(@Valid UpdateUserRequest request);
    UserResponse findById(UUID id);
    List<UserResponse> findAll();
    void delete(UUID id);
}