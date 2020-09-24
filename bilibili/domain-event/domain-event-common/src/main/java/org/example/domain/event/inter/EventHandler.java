package org.example.domain.event.inter;

import org.example.domain.event.model.DomainEvent;

public interface EventHandler<E extends DomainEvent> {
	void handler(E event);

}
