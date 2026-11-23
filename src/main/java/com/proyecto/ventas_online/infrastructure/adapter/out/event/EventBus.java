package com.proyecto.ventas_online.infrastructure.adapter.out.event;

import com.proyecto.ventas_online.application.event.DomainEventListener;
import com.proyecto.ventas_online.application.ports.out.DomainEventPublisher;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class EventBus implements DomainEventPublisher {

    private final Map<Class<?>, List<DomainEventListener<?>>> listeners = new ConcurrentHashMap<>();

    public EventBus(List<DomainEventListener<?>> initial) {
        initial.forEach(this::subscribe);
    }

    public void subscribe(DomainEventListener<?> listener) {
        listeners.computeIfAbsent(listener.eventType(), k -> new CopyOnWriteArrayList<>())
                .add(listener);
    }

    public void unsubscribe(DomainEventListener<?> listener) {
        List<DomainEventListener<?>> list = listeners.get(listener.eventType());
        if (list != null) list.remove(listener);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void publish(Object event) {
        for (DomainEventListener<?> listener : listeners.getOrDefault(event.getClass(), List.of())) {
            try {
                ((DomainEventListener<Object>) listener).onEvent(event);
            } catch (RuntimeException e) {
                log.error("Falló el listener {} con el evento {}", listener.getClass().getSimpleName(), event, e);
            }
        }
    }
}