package com.proyecto.ventas_online.domain.factory;

import com.proyecto.ventas_online.domain.factory.properties.LoginProperties;
import com.proyecto.ventas_online.domain.model.Role;
import com.proyecto.ventas_online.domain.model.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DefaultUserFactory {

    private final LoginProperties loginProperties;

    public List<User> createDefaultUsers(){
        return loginProperties.users().stream()
                .map(u ->
                    User.create(u.firstName(), u.lastName(),
                            u.email(), u.rawPassword(), u.role())
                ).toList();
    }
}
