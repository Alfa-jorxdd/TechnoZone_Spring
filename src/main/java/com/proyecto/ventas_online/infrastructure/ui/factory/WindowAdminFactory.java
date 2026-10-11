package com.proyecto.ventas_online.infrastructure.ui.factory;

import com.proyecto.ventas_online.domain.model.Role;
import com.proyecto.ventas_online.infrastructure.ui.admin.WindowAdmin;
import com.proyecto.ventas_online.infrastructure.ui.WindowUser;
import org.springframework.stereotype.Component;

@Component
public class WindowAdminFactory extends WindowFactory {

    @Override
    public Role role() {
        return Role.ADMIN;
    }

    @Override
    public WindowUser createWindow(Runnable r) {
        return new WindowAdmin(r);
    }
}
