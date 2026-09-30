package com.proyecto.ventas_online.application.ports.out;

import com.proyecto.ventas_online.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    Optional<User> findUserById(UUID id);
    User saveUser(User userBySave);
    User deleteUser(UUID id);
    User updateUser(User userByUpdate);
}
