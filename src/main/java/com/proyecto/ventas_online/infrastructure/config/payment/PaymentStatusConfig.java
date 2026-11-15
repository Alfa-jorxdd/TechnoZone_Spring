package com.proyecto.ventas_online.infrastructure.config.payment;

import com.proyecto.ventas_online.application.ports.in.payment.PaymentStatusUseCase;
import com.proyecto.ventas_online.application.ports.out.CartRepositoryPort;
import com.proyecto.ventas_online.application.ports.out.ProductRepositoryPort;
import com.proyecto.ventas_online.application.ports.out.PurchaseRepositoryPort;
import com.proyecto.ventas_online.application.service.payment.status.CompletedPayment;
import com.proyecto.ventas_online.application.service.payment.status.FailedPayment;
import com.proyecto.ventas_online.application.service.payment.status.PendingApprovalPayment;
import com.proyecto.ventas_online.application.service.payment.status.register.PaymentStatusUseCaseRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PaymentStatusConfig {
    @Bean
    public CompletedPayment completedPayment(
            ProductRepositoryPort productRepositoryPort,
            PurchaseRepositoryPort purchaseRepositoryPort,
            CartRepositoryPort cartRepositoryPort
    ){
        return new CompletedPayment(
                productRepositoryPort,
                purchaseRepositoryPort,
                cartRepositoryPort);
    }

    @Bean
    public FailedPayment failedPayment(
            PurchaseRepositoryPort purchaseRepositoryPort
    ){
        return new FailedPayment(purchaseRepositoryPort);
    }

    @Bean
    public PendingApprovalPayment pendingApprovalPayment(
            PurchaseRepositoryPort purchaseRepositoryPort
    ){
        return new PendingApprovalPayment(purchaseRepositoryPort);
    }

    @Bean
    public PaymentStatusUseCaseRegistry paymentStatusUseCaseRegistry(
            List<PaymentStatusUseCase> handlers
    ){
        return new PaymentStatusUseCaseRegistry(handlers);
    }
}
