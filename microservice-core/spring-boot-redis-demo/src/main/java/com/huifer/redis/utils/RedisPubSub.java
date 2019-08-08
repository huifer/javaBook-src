package com.huifer.redis.utils;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * redis 订阅-发布
 */
public class RedisPubSub implements MessageListener {
	@Override
	public void onMessage(Message message, byte[] bytes) {
		System.out.println();
	}
}
