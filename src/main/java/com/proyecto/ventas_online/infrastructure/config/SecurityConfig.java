package com.proyecto.ventas_online.infrastructure.config;

import com.proyecto.ventas_online.application.ports.out.PasswordHasher;
import com.proyecto.ventas_online.infrastructure.adapter.out.security.BCryptPasswordHasher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordHasher passwordHasher() {
        return new BCryptPasswordHasher();
    }
}
