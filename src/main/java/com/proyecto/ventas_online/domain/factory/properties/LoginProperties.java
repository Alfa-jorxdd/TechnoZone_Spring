package com.proyecto.ventas_online.domain.factory.properties;

import com.proyecto.ventas_online.domain.model.Role;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "login")
public record LoginProperties (List<UserProperties> users){
    public record UserProperties(
       String firstName,
       String lastName,
       String email,
       String rawPassword,
       Role role
    ){ }
}
