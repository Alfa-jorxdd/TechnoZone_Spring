package com.proyecto.ventas_online.application.ports.out;

import com.proyecto.ventas_online.application.dto.user.UserSaveResponseDTO;
import com.proyecto.ventas_online.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    Optional<User> findUserById(UUID id);
    UserSaveResponseDTO saveUser(User userBySave);
    void deleteUser(UUID id);
    void updateUser(User userByUpdate);
}
