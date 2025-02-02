package org.example.domain.event.model;

import java.util.ArrayList;
import java.util.List;

public abstract class EventCommon {

	private List<DomainEvent> events;

	protected final void registerEvent(DomainEvent event) {
		getEvents().add(event);
	}


	public final List<DomainEvent> getEvents() {
		if (events == null) {
			events = new ArrayList<>();
		}
		return events;
	}

	public	final void cleanEvents() {
		getEvents().clear();
	}

}
