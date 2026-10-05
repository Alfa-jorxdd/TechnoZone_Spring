package com.proyecto.ventas_online.infrastructure.config;

import com.proyecto.ventas_online.application.ports.out.*;
import com.proyecto.ventas_online.application.service.*;
import com.proyecto.ventas_online.domain.factory.DefaultCategoryFactory;
import com.proyecto.ventas_online.domain.factory.DefaultProductsFactory;
import com.proyecto.ventas_online.domain.factory.DefaultUserFactory;
import com.proyecto.ventas_online.domain.model.Category;
import com.proyecto.ventas_online.infrastructure.adapter.out.session.InMemorySessionContext;
import com.proyecto.ventas_online.infrastructure.mappers.ProductMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class BeanConfiguration {

    @Bean
    public ProductService productService(ProductRepositoryPort productRepositoryPort,
                                         CategoryRepositoryPort categoryRepositoryPort) {
        return new ProductService(productRepositoryPort, categoryRepositoryPort);
    }

    @Bean
    public UserService userService(UserRepositoryPort repositoryPort,
                                   PasswordHasher passwordHasher){
        return new UserService(repositoryPort, passwordHasher);
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

    @Bean
    CommandLineRunner seedData(CategoryService categoryService,
                               ProductService productService,
                               UserService userService,
                               ProductMapper productMapper) {
        return args -> {
            Map<String, UUID> categories = new HashMap<>();
            for (Category c : DefaultCategoryFactory.createDefaults()) {
                Category saved = categoryService.createCategory(c);
                categories.put(saved.getName(), saved.getId());
            }

            DefaultProductsFactory.createDefaults(categories)
                    .forEach(productService::createProduct);

            DefaultUserFactory.createDefaultUsers().stream()
                    .map(productMapper::toCommand).forEach(userService::createUser);
        };
    }
}
