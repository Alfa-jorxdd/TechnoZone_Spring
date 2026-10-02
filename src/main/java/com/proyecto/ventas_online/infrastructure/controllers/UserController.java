package com.proyecto.ventas_online.infrastructure.controllers;

import com.proyecto.ventas_online.application.ports.in.user.*;
import com.proyecto.ventas_online.domain.model.User;
import com.proyecto.ventas_online.infrastructure.controllers.api.UserApi;
import com.proyecto.ventas_online.infrastructure.dto.user.*;
import com.proyecto.ventas_online.infrastructure.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Component
@Validated
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserMapper userMapper;

    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @Override
    public UserResponse create(CreateUserRequest request) {
        User userToSave = createUserUseCase
                .createUser(userMapper.toCommand(request));
        return userMapper.toResponse(userToSave);
    }

    @Override
    public UserResponse update(UpdateUserRequest request) {
        User userToUpdate = updateUserUseCase
                .updateUser(userMapper.toCommand(request));
        return userMapper.toResponse(userToUpdate);
    }

    @Override
    public UserResponse findById(UUID id) {
        return userMapper.toResponse(getUserUseCase.findUserById(id));
    }

    @Override
    public List<UserResponse> findAll() {
        return getAllUsersUseCase.getAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        deleteUserUseCase.deleteUser(id);
    }
}
