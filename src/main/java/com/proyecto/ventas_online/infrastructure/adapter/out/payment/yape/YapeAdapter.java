package com.proyecto.ventas_online.infrastructure.adapter.out.payment.yape;

import com.proyecto.ventas_online.application.ports.in.payment.PaymentInput;
import com.proyecto.ventas_online.application.ports.out.payment.PaymentGatewayPort;
import com.proyecto.ventas_online.domain.model.PaymentMethod;
import com.proyecto.ventas_online.domain.model.PaymentStatus;
import com.proyecto.ventas_online.infrastructure.adapter.out.payment.yape.dto.YapeInput;
import com.proyecto.ventas_online.infrastructure.adapter.out.payment.yape.dto.YapeRequest;
import com.proyecto.ventas_online.infrastructure.adapter.out.payment.yape.dto.YapeResponse;
import com.proyecto.ventas_online.infrastructure.adapter.out.payment.yape.sdk.YapeSdk;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class YapeAdapter implements PaymentGatewayPort<YapeRequest, YapeResponse> {

    private final YapeSdk yapeSdk;

    @Override
    public PaymentMethod method() {
        return PaymentMethod.YAPE;
    }

    @Override
    public YapeRequest buildRequest(PaymentInput input, BigDecimal amount) {
        if (!(input instanceof YapeInput(String numberPhone))){
            throw new IllegalArgumentException("YapeAdapter requiere YapeInput");
        }
        return new YapeRequest(numberPhone, amount);
    }

    @Override
    public YapeResponse charge(YapeRequest request) {
        String transactionId = yapeSdk.yapear(request.numero(), request.amount());
        return new YapeResponse(
                PaymentStatus.COMPLETED,
                transactionId,
                "Yapeado " + request.amount() + " to phone number '" + request.numero() + "'"
        );
    }
}
