package org.example.domain.event.inter;

import org.example.domain.event.model.DomainEvent;

public class EventPublisherImpl implements EventPublisher {


	@Override
	public void push(DomainEvent domainEvent) {
		System.out.println("推送消息");
	}
}
