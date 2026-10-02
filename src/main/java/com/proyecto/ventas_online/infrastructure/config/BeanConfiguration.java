package com.proyecto.ventas_online.infrastructure.config;

import com.proyecto.ventas_online.application.ports.out.*;
import com.proyecto.ventas_online.application.service.*;
import com.proyecto.ventas_online.infrastructure.adapter.out.session.InMemorySessionContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ProductService productService(ProductRepositoryPort productRepositoryPort,
                                         CategoryRepositoryPort categoryRepositoryPort) {
        return new ProductService(productRepositoryPort, categoryRepositoryPort);
    }

    @Bean
    public UserService userService(UserRepositoryPort repositoryPort,
                                   PasswordHasher passwordHasher,
                                   CartRepositoryPort cartRepositoryPort){
        return new UserService(repositoryPort, cartRepositoryPort, passwordHasher);
    }

    @Bean
    public CartService cartService(CartRepositoryPort cartRepositoryPort,
                                   ProductRepositoryPort productRepositoryPort,
                                   UserRepositoryPort userRepositoryPort,
                                   SessionContext sessionContext){
        return new CartService(cartRepositoryPort, productRepositoryPort
                , userRepositoryPort, sessionContext);
    }

    @Bean
    public CategoryService categoryService(CategoryRepositoryPort categoryRepositoryPort){
        return new CategoryService(categoryRepositoryPort);
    }

    @Bean
    public AuthService authService(PasswordHasher passwordHasher,
                                   SessionContext sessionContext,
                                   UserRepositoryPort userRepositoryPort){
        return new AuthService(passwordHasher, sessionContext, userRepositoryPort);
    }

    @Bean
    public SessionContext sessionContext() {
        return InMemorySessionContext.getInstance();
    }
}
