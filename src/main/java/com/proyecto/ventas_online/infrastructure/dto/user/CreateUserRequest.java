package com.proyecto.ventas_online.infrastructure.dto.user;

import com.proyecto.ventas_online.domain.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 50, message = "Name cannot exceed 50 characters")
        String name,

        @NotBlank(message = "Lastname is required")
        @Size(max = 60, message = "Lastname cannot exceed 60 characters")
        String lastname,

        @NotBlank(message = "Email is required")
        @Email(message =  "Email format is invalid")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 72, message = "Password must be between 8 and 72 characters")
        String password,

        @NotNull(message = "Role is required")
        Role role
) {}
