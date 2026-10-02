package com.proyecto.ventas_online.application.ports.out;

import com.proyecto.ventas_online.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    Optional<User> findUserById(UUID id);
    Optional<User> findUserByEmail(String email);
    User saveUser(User userBySave);
    User deleteUser(UUID id);
    User updateUser(User userByUpdate);
    List<User> getAll();
}
