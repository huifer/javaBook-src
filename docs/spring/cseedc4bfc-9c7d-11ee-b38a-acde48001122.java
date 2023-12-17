package org.example.domain.event.model;

import java.util.UUID;

public abstract class DomainEvent {
	private final String uid;

	private final long createAt;

	public DomainEvent() {
		this.uid = UUID.randomUUID().toString();
		this.createAt = System.currentTimeMillis();
	}

	public long getCreateAt() {
		return createAt;
	}

	@Override
	public String toString() {
		return "DomainEvent{" +
				"uid='" + uid + '\'' +
				", createAt=" + createAt +
				'}';
	}
}
