package com.proyecto.ventas_online.domain.event;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductUpdatePriceEvent(
        UUID idProduct,
        BigDecimal price,
        int stock) {
}
