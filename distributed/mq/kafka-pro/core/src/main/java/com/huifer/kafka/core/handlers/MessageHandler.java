package com.huifer.kafka.core.handlers;

public interface MessageHandler {
	void execute(String message);
}
