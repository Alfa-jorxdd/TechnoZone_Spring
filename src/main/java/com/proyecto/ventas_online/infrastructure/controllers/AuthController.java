package com.proyecto.ventas_online.infrastructure.controllers;

import com.proyecto.ventas_online.application.ports.in.auth.LoginUseCase;
import com.proyecto.ventas_online.application.ports.in.auth.LogoutUseCase;
import com.proyecto.ventas_online.application.ports.in.user.CreateUserUseCase;
import com.proyecto.ventas_online.domain.model.Role;
import com.proyecto.ventas_online.domain.model.User;
import com.proyecto.ventas_online.infrastructure.controllers.api.AuthApi;
import com.proyecto.ventas_online.infrastructure.dto.auth.LoginRequest;
import com.proyecto.ventas_online.infrastructure.dto.auth.RegisterRequest;
import com.proyecto.ventas_online.infrastructure.dto.user.CreateUserCommand;
import com.proyecto.ventas_online.infrastructure.dto.user.UserResponse;
import com.proyecto.ventas_online.infrastructure.mappers.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final UserMapper userMapper;

    private final LoginUseCase loginUseCase;
    private final LogoutUseCase logoutUseCase;
    private final CreateUserUseCase createUserUseCase;

    @Override
    public UserResponse login(LoginRequest request) {
        User userToLogin = loginUseCase.login(request.email(), request.password());
        return userMapper.toResponse(userToLogin);
    }

    @Override
    public void logout() {
        logoutUseCase.logout();
    }

    @Override
    public UserResponse register(RegisterRequest request) {
        CreateUserCommand command = new CreateUserCommand(
                request.name(),
                request.lastname(),
                request.email(),
                request.password(),
                Role.CLIENT
        );
        return userMapper.toResponse(createUserUseCase.createUser(command));
    }
}
