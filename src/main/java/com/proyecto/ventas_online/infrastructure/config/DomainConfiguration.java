package com.proyecto.ventas_online.infrastructure.config;

import com.proyecto.ventas_online.domain.factory.DefaultUserFactory;
import com.proyecto.ventas_online.domain.factory.properties.LoginProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {
    @Bean
    public DefaultUserFactory defaultUserFactory(LoginProperties loginProperties){
        return new DefaultUserFactory(loginProperties);
    }
}
