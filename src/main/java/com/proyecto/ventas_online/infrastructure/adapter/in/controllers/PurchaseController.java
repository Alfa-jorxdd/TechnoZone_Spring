package com.proyecto.ventas_online.infrastructure.adapter.in.controllers;

import com.proyecto.ventas_online.application.ports.in.payment.PaymentInput;
import com.proyecto.ventas_online.application.ports.in.purchase.CheckoutUseCase;
import com.proyecto.ventas_online.application.ports.in.purchase.ConfirmPurchaseUseCase;
import com.proyecto.ventas_online.application.ports.in.purchase.GetPurchaseHistoryUseCase;
import com.proyecto.ventas_online.application.ports.in.purchase.GetPurchaseUseCase;
import com.proyecto.ventas_online.infrastructure.adapter.in.controllers.api.PurchaseApi;
import com.proyecto.ventas_online.infrastructure.dto.purchase.CheckoutResultResponse;
import com.proyecto.ventas_online.infrastructure.dto.purchase.PurchaseResponse;
import com.proyecto.ventas_online.infrastructure.mappers.PurchaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PurchaseController implements PurchaseApi {

    private final CheckoutUseCase checkoutUseCase;
    private final ConfirmPurchaseUseCase confirmPurchaseUseCase;
    private final GetPurchaseUseCase getPurchaseUseCase;
    private final GetPurchaseHistoryUseCase getPurchaseHistoryUseCase;
    private final PurchaseMapper mapper;

    @Override
    public CheckoutResultResponse checkout(PaymentInput input) {
        Objects.requireNonNull(input, "El método de pago es obligatorio");
        return mapper.toResponse(checkoutUseCase.checkout(input));
    }

    @Override
    public PurchaseResponse confirm(String transactionId) {
        if (transactionId == null || transactionId.isBlank()) {
            throw new IllegalArgumentException("La transacción es obligatoria");
        }
        return mapper.toResponse(confirmPurchaseUseCase.confirm(transactionId));
    }

    @Override
    public PurchaseResponse findById(UUID idPurchase) {
        return mapper.toResponse(getPurchaseUseCase.getById(idPurchase));
    }

    @Override
    public List<PurchaseResponse> getHistory() {
        return mapper.toResponse(getPurchaseHistoryUseCase.getHistory());
    }
}
