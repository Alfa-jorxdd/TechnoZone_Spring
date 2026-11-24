package com.proyecto.ventas_online.infrastructure.adapter.in.controllers.api;

import com.proyecto.ventas_online.application.ports.in.payment.PaymentInput;
import com.proyecto.ventas_online.infrastructure.dto.purchase.CheckoutResultResponse;
import com.proyecto.ventas_online.infrastructure.dto.purchase.PurchaseResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
public interface PurchaseApi {
    CheckoutResultResponse checkout(PaymentInput input);
    PurchaseResponse confirm(String transactionId);
    PurchaseResponse cancel(UUID idPurchase);
    PurchaseResponse findById(UUID idPurchase);
    List<PurchaseResponse> getHistory();
}
