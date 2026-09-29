package com.proyecto.ventas_online.infrastructure.adapter.out.persistence;

import com.proyecto.ventas_online.application.dto.user.UserSaveResponseDTO;
import com.proyecto.ventas_online.application.ports.out.UserRepositoryPort;
import com.proyecto.ventas_online.domain.model.User;
import com.proyecto.ventas_online.infrastructure.model.UserEntity;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class InMemoryUserRepository implements UserRepositoryPort {

    private final HashMap<Long, UserEntity> repositoryUser = new HashMap<>();

    @Override
    public Optional<User> findUserById(UUID id) {
        return Optional.empty();
    }

    @Override
    public UserSaveResponseDTO saveUser(User userBySave) {
        return null;
    }

    @Override
    public void deleteUser(UUID id) {

    }

    @Override
    public void updateUser(User userByUpdate) {

    }
}
