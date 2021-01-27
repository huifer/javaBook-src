package org.example.domain.event.example.service;

import org.example.domain.event.example.entity.UserEntity;
import org.example.domain.event.example.event.create.user.CreateUserSendMessageEvent;
import org.example.domain.event.example.event.handler.CreateUserSendMessageEventHandler;
import org.example.domain.event.inter.EventPublisher;
import org.example.domain.event.model.DomainEvent;

public class Main {
	public static void main(String[] args) {
		EventPublisher eventPublisher = new EventPublisher() {
			@Override
			public void push(DomainEvent domainEvent) {
				System.out.println("推送消息");
				// 模拟事件的后续执行
				if (domainEvent instanceof CreateUserSendMessageEvent) {
					new CreateUserSendMessageEventHandler().handler((CreateUserSendMessageEvent) domainEvent);
				}
			}
		};

		UserEntity userEntity = UserEntity.CreateUser("name", "YourMail");

		for (DomainEvent event : userEntity.getEvents()) {
			eventPublisher.push(event);
		}
	}
}
