package com.proyecto.ventas_online.infrastructure.adapter.out.session;

import com.proyecto.ventas_online.application.ports.out.SessionContext;
import com.proyecto.ventas_online.domain.exception.session.NoActiveSessionException;
import com.proyecto.ventas_online.domain.model.Role;

import java.util.UUID;

public class InMemorySessionContext implements SessionContext {

    private static final InMemorySessionContext INSTANCE = new InMemorySessionContext();

    private record Session(UUID userId, Role role) {}

    private Session session;

    private InMemorySessionContext() {}

    public static InMemorySessionContext getInstance() {
        return INSTANCE;
    }

    @Override
    public void login(UUID userId, Role role) {
        this.session = new Session(userId, role);
    }

    @Override
    public void logout() {
        this.session = null;
    }

    @Override
    public UUID getCurrentUserId() {
        return requireSession().userId;
    }

    @Override
    public Role getCurrentRole() {
        return requireSession().role;
    }

    @Override
    public boolean isLoggedIn() {
        return session != null;
    }

    private Session requireSession(){
        if (session == null) throw new NoActiveSessionException();
        return session;
    }
}
