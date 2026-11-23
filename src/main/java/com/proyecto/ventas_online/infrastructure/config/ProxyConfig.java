package com.proyecto.ventas_online.infrastructure.config;

import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.CategoryController;
import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.ProductController;
import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.UserController;
import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.api.CategoryApi;
import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.api.ProductApi;
import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.api.UserApi;
import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.proxy.CategoryApiProxy;
import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.proxy.ProductApiProxy;
import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.proxy.UserApiProxy;
import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.proxy.helper.AccessGuard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ProxyConfig {

    @Bean
    @Primary
    public ProductApi productApi(ProductController real, AccessGuard guard) {
        return new ProductApiProxy(real, guard);
    }

    @Bean
    @Primary
    public CategoryApi categoryApi(CategoryController real, AccessGuard guard) {
        return new CategoryApiProxy(guard, real);
    }

    @Bean
    @Primary
    public UserApi userApi(UserController real, AccessGuard guard) {
        return new UserApiProxy(real, guard);
    }
}