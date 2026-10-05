package com.proyecto.ventas_online.infrastructure.ui.factory;

import com.proyecto.ventas_online.domain.model.Role;
import com.proyecto.ventas_online.infrastructure.controllers.api.*;
import com.proyecto.ventas_online.infrastructure.ui.client.WindowClient;
import com.proyecto.ventas_online.infrastructure.ui.WindowUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WindowClientFactory extends WindowFactory {

    private final ProductApi productApi;
    private final CartApi cartApi;
    private final CategoryApi categoryApi;
    private final UserApi userApi;
    private final AuthApi authApi;

    @Override
    public Role role() {
        return Role.CLIENT;
    }

    @Override
    public WindowUser createWindow(Runnable r) {
        return new WindowClient(r, productApi, categoryApi, cartApi, userApi, authApi);
    }
    
}
