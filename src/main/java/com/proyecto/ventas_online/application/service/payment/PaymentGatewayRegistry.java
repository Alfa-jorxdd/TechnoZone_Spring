package com.proyecto.ventas_online.application.service.payment;

import com.proyecto.ventas_online.application.ports.in.payment.PaymentInput;
import com.proyecto.ventas_online.application.ports.out.payment.PaymentConfirmationPort;
import com.proyecto.ventas_online.application.ports.out.payment.PaymentGatewayPort;
import com.proyecto.ventas_online.application.ports.out.payment.PaymentRequest;
import com.proyecto.ventas_online.application.ports.out.payment.PaymentResponse;
import com.proyecto.ventas_online.domain.exception.payment.PaymentConfirmationNotSupportedException;
import com.proyecto.ventas_online.domain.exception.payment.UnsupportedPaymentMethodException;
import com.proyecto.ventas_online.domain.model.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PaymentGatewayRegistry {
    private final Map<PaymentMethod, PaymentGatewayPort<?, ?>> gateways;
    private final Map<PaymentMethod, PaymentConfirmationPort<?>> confirms;

    public PaymentGatewayRegistry(List<PaymentGatewayPort<?,?>> gateways,
                                  List<PaymentConfirmationPort<?>> confirms){
        this.gateways = gateways.stream().collect(Collectors.toMap(
                PaymentGatewayPort::method, Function.identity()
        ));
        this.confirms = confirms.stream().collect(
                Collectors.toMap(PaymentConfirmationPort::method, Function.identity())
        );
    }

    @SuppressWarnings("unchecked")
    public PaymentResponse charge(PaymentInput input, BigDecimal amount){
        PaymentGatewayPort<PaymentRequest, ?> gateway = (PaymentGatewayPort<PaymentRequest, ?>) gateways.get(input.method());
        if (gateway == null){
            throw new UnsupportedPaymentMethodException(input.method().name());
        }
        PaymentRequest paypalRequest = gateway.buildRequest(input,amount);
        return gateway.charge(paypalRequest);
    }

    public PaymentResponse confirm(PaymentMethod method, String transactionId){
        PaymentConfirmationPort<?> portConfirm = confirms.get(method);
        if (portConfirm == null){
            throw new PaymentConfirmationNotSupportedException(method);
        }
        return portConfirm.confirm(transactionId);
    }
}
