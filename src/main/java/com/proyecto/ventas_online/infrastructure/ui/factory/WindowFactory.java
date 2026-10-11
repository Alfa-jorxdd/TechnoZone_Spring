package com.proyecto.ventas_online.infrastructure.ui.factory;

import com.proyecto.ventas_online.domain.model.Role;
import com.proyecto.ventas_online.infrastructure.ui.WindowUser;

public abstract class WindowFactory {
    public abstract Role role();
    public abstract WindowUser createWindow(Runnable r);
    
    public WindowUser open(Runnable r){
        WindowUser windowUser = createWindow(r);
        windowUser.display();
        return windowUser;
    }
}
