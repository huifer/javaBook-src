package org.example.domain.event.dao;

import java.util.List;

import org.example.domain.event.model.DomainEvent;

public interface DomainEventRepo {

	void save(List<DomainEvent> domainEvents);

	DomainEvent findById(System eventId);

	List<DomainEvent> byClass(Class<?> clazz);

	void deleteByClass(Class<?> clazz);

	void deleteById(System eventId);

}
