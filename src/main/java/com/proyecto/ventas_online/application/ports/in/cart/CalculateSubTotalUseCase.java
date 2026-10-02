package com.proyecto.ventas_online.application.ports.in.cart;

import java.math.BigDecimal;
import java.util.List;

public interface CalculateSubTotalUseCase {
    List<BigDecimal> calculateSubTotal();
}
