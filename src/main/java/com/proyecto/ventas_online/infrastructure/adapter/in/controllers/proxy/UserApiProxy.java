package com.proyecto.ventas_online.infrastructure.adapter.in.controllers.proxy;

import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.api.UserApi;
import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.proxy.helper.AccessGuard;
import com.proyecto.ventas_online.infrastructure.dto.user.CreateUserRequest;
import com.proyecto.ventas_online.infrastructure.dto.user.UpdateUserRequest;
import com.proyecto.ventas_online.infrastructure.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class UserApiProxy implements UserApi {

    private final UserApi real;
    private final AccessGuard guard;


    @Override public UserResponse create(CreateUserRequest request) {
        guard.requireAdmin();
        return real.create(request);
    }
    @Override public UserResponse update(UpdateUserRequest request) {
        guard.requireAdmin();
        return real.update(request);
    }
    @Override public List<UserResponse> findAll() {
        guard.requireAdmin();
        return real.findAll();
    }
    @Override public void delete(UUID id) {
        guard.requireAdmin();
        real.delete(id);
    }

    @Override public UserResponse findById(UUID id) {
        guard.requireAdminOrSelf(id);
        return real.findById(id);
    }
}
