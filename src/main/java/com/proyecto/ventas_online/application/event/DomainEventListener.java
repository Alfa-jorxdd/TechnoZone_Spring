package com.proyecto.ventas_online.application.event;

public interface DomainEventListener<E> {
    Class<E> eventType();
    void onEvent(E event);
}
