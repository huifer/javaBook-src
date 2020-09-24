package org.example.domain.event.example.event.create.user;

import org.example.domain.event.model.DomainEvent;

public class CreateUserSendMessageEvent extends DomainEvent {
	private final String mail;

	public CreateUserSendMessageEvent(String mail) {
		super();
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}

}
