package com.proyecto.ventas_online.application.ports.out;

import com.proyecto.ventas_online.domain.model.Role;

import java.util.UUID;

public interface SessionContext {
    void login(UUID userId, Role role);
    void logout();
    UUID getCurrentUserId();
    Role getCurrentRole();
    boolean isLoggedIn();
}
