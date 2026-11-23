package com.proyecto.ventas_online.domain.event;


import java.util.UUID;

public record ProductDeletedEvent(UUID idProduct) {}
