package org.example.domain.event.inter;

import org.example.domain.event.model.DomainEvent;

public interface EventPublisher {
	void push(DomainEvent domainEvent);
}
