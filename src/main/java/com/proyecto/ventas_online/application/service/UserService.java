package com.proyecto.ventas_online.application.service;

import com.proyecto.ventas_online.application.ports.in.user.*;
import com.proyecto.ventas_online.application.ports.out.CartRepositoryPort;
import com.proyecto.ventas_online.application.ports.out.PasswordHasher;
import com.proyecto.ventas_online.application.ports.out.UserRepositoryPort;
import com.proyecto.ventas_online.domain.exception.cart.CartNotFoundException;
import com.proyecto.ventas_online.domain.exception.user.DuplicateUserEmailException;
import com.proyecto.ventas_online.domain.exception.user.UserNotFoundException;
import com.proyecto.ventas_online.domain.model.User;
import com.proyecto.ventas_online.infrastructure.dto.auth.RegisterRequest;
import com.proyecto.ventas_online.infrastructure.dto.user.CreateUserCommand;
import com.proyecto.ventas_online.infrastructure.dto.user.UpdateUserCommand;
import com.proyecto.ventas_online.infrastructure.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class UserService implements
        CreateUserUseCase,
        DeleteUserUseCase,
        GetAllUsersUseCase,
        GetUserUseCase,
        UpdateUserUseCase{

    private final UserRepositoryPort userRepository;
    private final PasswordHasher passwordHasher;

    @Override
    public User createUser(CreateUserCommand command) {
        if (userRepository.findUserByEmail(command.email()).isPresent()){
            throw new DuplicateUserEmailException(command.email());
        }
        String hash = passwordHasher.encode(command.rawPassword());
        User userToSave = User.create(
                command.name(),
                command.lastname(),
                command.email(),
                hash,
                command.role()
        );
        return userRepository.saveUser(userToSave);
    }

    @Override
    public void deleteUser(UUID id) {
        if (userRepository.findUserById(id).isEmpty()){
            throw new UserNotFoundException();
        }
        userRepository.deleteUser(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User findUserById(UUID id) {
        return userRepository.findUserById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User updateUser(UpdateUserCommand command) {
        User existing = userRepository.findUserById(command.id())
                .orElseThrow(UserNotFoundException::new);

        userRepository.findUserByEmail(command.email())
                .filter(u -> !u.getId().equals(command.id()))
                .ifPresent(u -> { throw new DuplicateUserEmailException(command.email()); });

        String hash = (command.rawPassword() == null || command.rawPassword().isBlank())
                ? existing.getPasswordHash()
                : passwordHasher.encode(command.rawPassword());

        User updated = User.reconstitute(
                command.id(), command.name(), command.lastname(),
                command.email(), hash, command.role());

        return userRepository.updateUser(updated);
    }
}
