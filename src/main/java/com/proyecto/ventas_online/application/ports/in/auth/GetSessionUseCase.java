package com.proyecto.ventas_online.application.ports.in.auth;

import com.proyecto.ventas_online.domain.model.Session;

public interface GetSessionUseCase {
    Session getCurrentSession();
}
