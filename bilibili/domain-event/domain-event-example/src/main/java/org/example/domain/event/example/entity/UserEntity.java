package org.example.domain.event.example.entity;

import org.example.domain.event.example.event.create.user.CreateUserSendMessageEvent;
import org.example.domain.event.model.EventCommon;

public class UserEntity extends EventCommon {

	private final String name;

	private final String mail;

	public UserEntity(String name, String mail) {
		this.name = name;
		this.mail = mail;
	}

	public static UserEntity CreateUser(String name, String mail) {
		UserEntity userEntity = new UserEntity(name, mail);
		userEntity.registerEvent(new CreateUserSendMessageEvent(mail));
		return userEntity;
	}

}
