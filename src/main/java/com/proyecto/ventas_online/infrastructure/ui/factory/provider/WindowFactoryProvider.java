package com.proyecto.ventas_online.infrastructure.ui.factory.provider;

import com.proyecto.ventas_online.domain.model.Role;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.proyecto.ventas_online.infrastructure.ui.factory.WindowFactory;
import org.springframework.stereotype.Component;

@Component
public class WindowFactoryProvider {
    
    private final Map<Role, WindowFactory> factories;
    
    public WindowFactoryProvider(List<WindowFactory> factories){
        this.factories = factories.stream()
                .collect(Collectors.toMap(WindowFactory::role, windowFactory -> windowFactory));
    }

    public void open(Role role, Runnable r){
        WindowFactory factory = factories.get(role);
        if (factory == null) {
            throw new IllegalStateException("Sin ventana para el rol " + role);
        }
        factory.createWindow(r).display();
    }
}
