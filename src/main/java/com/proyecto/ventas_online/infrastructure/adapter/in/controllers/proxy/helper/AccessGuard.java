package com.proyecto.ventas_online.infrastructure.adapter.in.controllers.proxy.helper;

import com.proyecto.ventas_online.application.ports.in.auth.GetSessionUseCase;
import com.proyecto.ventas_online.domain.exception.auth.UnauthorizedActionException;
import com.proyecto.ventas_online.domain.model.Role;
import com.proyecto.ventas_online.domain.model.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AccessGuard {

    private final GetSessionUseCase getSessionUseCase;

    public void requireAdmin() {
        Session session = getSessionUseCase.getCurrentSession();   // lanza NoActiveSessionException si no hay sesión
        if (session.role() != Role.ADMIN) {
            throw new UnauthorizedActionException();
        }
    }

    public void requireAdminOrSelf(UUID idUser) {
        Session session = getSessionUseCase.getCurrentSession();
        if (session.role() != Role.ADMIN && !session.userId().equals(idUser)) {
            throw new UnauthorizedActionException();
        }
    }
}
