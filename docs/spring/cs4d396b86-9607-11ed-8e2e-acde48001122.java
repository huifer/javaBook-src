package org.example.domain.event.example.event.handler;

import org.example.domain.event.example.event.create.user.CreateUserSendMessageEvent;
import org.example.domain.event.inter.EventHandler;

public class CreateUserSendMessageEventHandler implements EventHandler<CreateUserSendMessageEvent> {
	@Override
	public void handler(CreateUserSendMessageEvent event) {
		String mail = event.getMail();
		System.out.println("给" + mail + "发送邮件");
	}
}
