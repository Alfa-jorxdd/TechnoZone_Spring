package com.proyecto.ventas_online.infrastructure.ui.session;

import com.proyecto.ventas_online.application.ports.out.SessionContext;
import com.proyecto.ventas_online.domain.model.Role;
import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.api.AuthApi;
import com.proyecto.ventas_online.infrastructure.ui.auth.LoginFrame;
import com.proyecto.ventas_online.infrastructure.ui.factory.provider.WindowFactoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionRouter {
    private final SessionContext sessionContext;
    private final ApplicationContext ctx;
    private final WindowFactoryProvider windowFactoryProvider;
    private final AuthApi authApi;

    public void startAppSession(){
        if (sessionContext.isLoggedIn()){
            routeToDashboard();
        } else {
            routeToLogin();
        }
    }

    public void routeToLogin(){
        ctx.getBean(LoginFrame.class).setVisible(true);
    }

    public void routeToDashboard(){
        Role roleCurrentUser = sessionContext.getCurrentRole();
        ctx.getBean(LoginFrame.class).setVisible(false);

        windowFactoryProvider.open(roleCurrentUser, () -> {
            ctx.getBean(LoginFrame.class).setVisible(true);
            authApi.logout();
        });
    }
}
