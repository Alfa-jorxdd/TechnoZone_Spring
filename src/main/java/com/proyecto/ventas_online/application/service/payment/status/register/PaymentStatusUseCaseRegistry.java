package com.proyecto.ventas_online.application.service.payment.status.register;

import com.proyecto.ventas_online.application.ports.in.payment.PaymentStatusUseCase;
import com.proyecto.ventas_online.domain.model.PaymentStatus;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PaymentStatusUseCaseRegistry {
    private final Map<PaymentStatus, PaymentStatusUseCase> handlersByStatus;

    public PaymentStatusUseCaseRegistry(List<PaymentStatusUseCase> handlersList){
        handlersByStatus = handlersList.stream()
                .collect(Collectors.toMap(
                        PaymentStatusUseCase::getStatus, Function.identity()
                ));
    }

    public PaymentStatusUseCase getHandler(PaymentStatus paymentStatus){
        PaymentStatusUseCase handler = handlersByStatus.get(paymentStatus);
        if (handler == null){
            throw new IllegalArgumentException();
        }
        return handler;
    }
}
