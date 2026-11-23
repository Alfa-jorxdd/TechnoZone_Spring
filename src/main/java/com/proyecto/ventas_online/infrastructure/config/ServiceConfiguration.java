package com.proyecto.ventas_online.infrastructure.config;

import com.proyecto.ventas_online.application.event.DomainEventListener;
import com.proyecto.ventas_online.application.ports.in.payment.PaymentStatusUseCase;
import com.proyecto.ventas_online.application.ports.out.*;
import com.proyecto.ventas_online.application.ports.out.payment.PaymentConfirmationPort;
import com.proyecto.ventas_online.application.ports.out.payment.PaymentGatewayPort;
import com.proyecto.ventas_online.application.service.*;
import com.proyecto.ventas_online.application.service.payment.PaymentGatewayRegistry;
import com.proyecto.ventas_online.application.service.payment.status.register.PaymentStatusUseCaseRegistry;
import com.proyecto.ventas_online.domain.factory.DefaultCategoryFactory;
import com.proyecto.ventas_online.domain.factory.DefaultProductsFactory;
import com.proyecto.ventas_online.domain.factory.DefaultUserFactory;
import com.proyecto.ventas_online.domain.model.Category;
import com.proyecto.ventas_online.infrastructure.adapter.out.event.EventBus;
import com.proyecto.ventas_online.infrastructure.adapter.out.session.InMemorySessionContext;
import com.proyecto.ventas_online.infrastructure.mappers.ProductMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Configuration
public class ServiceConfiguration {

    @Bean
    public ProductService productService(ProductRepositoryPort productRepositoryPort,
                                         CategoryRepositoryPort categoryRepositoryPort,
                                         DomainEventPublisher publisher) {
        return new ProductService(productRepositoryPort, categoryRepositoryPort, publisher);
    }

    @Bean
    public CartProductUpdatedListener cartProductUpdatedListener(CartRepositoryPort carts){
        return new CartProductUpdatedListener(carts);
    }

    @Bean
    public CartProductDeletedListener cartProductDeletedListener(CartRepositoryPort cats){
        return new CartProductDeletedListener(cats);
    }

    public EventBus eventBus(List<DomainEventListener<?>> listeners){
        return new EventBus(listeners);
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
    public PurchaseService purchaseService(PurchaseRepositoryPort purchaseRepositoryPort,
                                           CartRepositoryPort cartRepositoryPort,
                                           SessionContext sessionContext,
                                           PaymentGatewayRegistry paymentGatewayRegistry,
                                           PaymentStatusUseCaseRegistry paymentStatusUseCaseRegistry,
                                           ProductRepositoryPort productRepositoryPort
                                           ){
        return new PurchaseService(purchaseRepositoryPort,
                cartRepositoryPort,
                sessionContext,
                paymentGatewayRegistry,
                paymentStatusUseCaseRegistry,
                productRepositoryPort);
    }

    @Bean
    public PaymentGatewayRegistry paymentGatewayRegister(
            List<PaymentGatewayPort<?,?>> gateways,
            List<PaymentConfirmationPort<?>> confirms
    ){
        return new PaymentGatewayRegistry(gateways,confirms);
    }

    @Bean
    public SessionContext sessionContext() {
        return InMemorySessionContext.getInstance();
    }

    @Bean
    CommandLineRunner seedData(CategoryService categoryService,
                               ProductService productService,
                               UserService userService,
                               ProductMapper productMapper,
                               DefaultUserFactory defaultUserFactory) {
        return args -> {
            Map<String, UUID> categories = new HashMap<>();
            for (Category c : DefaultCategoryFactory.createDefaults()) {
                Category saved = categoryService.createCategory(c);
                categories.put(saved.getName(), saved.getId());
            }

            DefaultProductsFactory.createDefaults(categories)
                    .forEach(productService::createProduct);

            defaultUserFactory.createDefaultUsers().stream()
                    .map(productMapper::toCommand).forEach(userService::createUser);
        };
    }
}
