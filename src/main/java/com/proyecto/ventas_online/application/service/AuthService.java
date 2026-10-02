package com.proyecto.ventas_online.application.service;

import com.proyecto.ventas_online.application.ports.in.auth.LoginUseCase;
import com.proyecto.ventas_online.application.ports.in.auth.LogoutUseCase;
import com.proyecto.ventas_online.application.ports.out.PasswordHasher;
import com.proyecto.ventas_online.application.ports.out.SessionContext;
import com.proyecto.ventas_online.application.ports.out.UserRepositoryPort;
import com.proyecto.ventas_online.domain.exception.auth.InvalidCredentialsException;
import com.proyecto.ventas_online.domain.exception.user.UserNotFoundException;
import com.proyecto.ventas_online.domain.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthService implements
        LoginUseCase,
        LogoutUseCase {

    private final PasswordHasher passwordHasher;

    private final SessionContext sessionContext;
    private final UserRepositoryPort userRepository;

    @Override
    public User login(String email, String rawPassword) {
        User userToLogin = userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        if (!passwordHasher.matches(rawPassword, userToLogin.getPasswordHash())){
            throw new InvalidCredentialsException();
        }
        sessionContext.login(userToLogin.getId(), userToLogin.getRole());
        return userToLogin;
    }

    @Override
    public void logout() {
        sessionContext.logout();
    }
}
