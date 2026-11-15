package com.proyecto.ventas_online.infrastructure.adapter.out.payment.paypal;

import com.paypal.sdk.PaypalServerSdkClient;
import com.paypal.sdk.controllers.OrdersController;
import com.paypal.sdk.exceptions.ApiException;
import com.paypal.sdk.models.*;
import com.proyecto.ventas_online.application.ports.in.payment.PaymentInput;
import com.proyecto.ventas_online.application.ports.out.payment.PaymentConfirmationPort;
import com.proyecto.ventas_online.application.ports.out.payment.PaymentGatewayPort;
import com.proyecto.ventas_online.domain.model.PaymentMethod;
import com.proyecto.ventas_online.domain.model.PaymentStatus;
import com.proyecto.ventas_online.infrastructure.adapter.out.payment.paypal.dto.PayPalInput;
import com.proyecto.ventas_online.infrastructure.adapter.out.payment.paypal.dto.PayPalPaymentRequest;
import com.proyecto.ventas_online.infrastructure.adapter.out.payment.paypal.dto.PayPalPaymentResponse;
import com.proyecto.ventas_online.infrastructure.adapter.out.payment.paypal.exception.PaymentFailedException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class PayPalClientAdapter implements PaymentGatewayPort<PayPalPaymentRequest, PayPalPaymentResponse>,
        PaymentConfirmationPort<PayPalPaymentResponse> {

    private final OrdersController orders;

    public PayPalClientAdapter(PaypalServerSdkClient client){
        this.orders = client.getOrdersController();
    }

    @Override
    public PaymentMethod method() {
        return PaymentMethod.PAYPAL;
    }

    @Override
    public PayPalPaymentRequest buildRequest(PaymentInput input, BigDecimal amount) {
        if (!(input instanceof PayPalInput)) {
            throw new IllegalArgumentException("PayPalClientAdapter requiere PayPalInput");
        }
        return new PayPalPaymentRequest(amount);
    }

    @Override
    public PayPalPaymentResponse charge(PayPalPaymentRequest request) {
        try {
            CreateOrderInput input = new CreateOrderInput.Builder(
                    null,
                    new OrderRequest.Builder(
                            CheckoutPaymentIntent.CAPTURE,
                            List.of(
                                    new PurchaseUnitRequest.Builder(
                                            new AmountWithBreakdown.Builder(
                                                    "USD",
                                                    request.amount().setScale(2, RoundingMode.HALF_UP).toPlainString()
                                            ).build()
                                    ).build())
                    ).build()
            ).build();

            Order order = orders.createOrder(input).getResult();

            String approvalUrl = order.getLinks().stream()
                    .filter(l -> "payer-action".equals(l.getRel()) || "approve".equals(l.getRel()))
                    .map(LinkDescription::getHref)
                    .findFirst()
                    .orElseThrow(() -> new PaymentFailedException("PayPal did not return an approval link"));

            return new PayPalPaymentResponse(
                    PaymentStatus.PENDING_APPROVAL,
                    order.getId(),
                    "Orden creada. Pendiente de aprbacion del comprador",
                    approvalUrl
            );
        } catch (ApiException | IOException e){
            throw new PaymentFailedException("Error con PayPal: " + e.getMessage());
        }
    }

    @Override
    public PayPalPaymentResponse confirm(String transactionId) {
        try {
            CaptureOrderInput input = new CaptureOrderInput.Builder(transactionId, null).build();
            Order order = orders.captureOrder(input).getResult();

            boolean completed = order.getStatus() == OrderStatus.COMPLETED;
            return new PayPalPaymentResponse(
                    completed ? PaymentStatus.COMPLETED : PaymentStatus.FAILED,
                    transactionId,
                    completed ? "Pago completado" : "PayPal no completó el pago: " + order.getStatus(),
                    null
            );
        } catch (ApiException | IOException e){
            throw new PaymentFailedException("Error al capturar la orden: " + e.getMessage(), e);
        }
    }
}
