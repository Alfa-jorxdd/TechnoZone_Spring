package com.proyecto.ventas_online.application.ports.out;

public interface DomainEventPublisher {
    void publish(Object event);
}
