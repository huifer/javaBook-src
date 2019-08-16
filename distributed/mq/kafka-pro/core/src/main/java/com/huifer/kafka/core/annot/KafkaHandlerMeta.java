package com.huifer.kafka.core.annot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KafkaHandlerMeta<T> {
	private T bean;
	private Method method;
	private Class<? extends Object> parameterType;

	private InputConsumer inputConsumer;

	private OutputProducer outputProducer;

	private Map<ErrorHandler, Method> errorHandlers = new HashMap<>();

	public void addErrorHandlers(Map<ErrorHandler, Method> errorHandlers) {
		this.errorHandlers.putAll(errorHandlers);
	}

	public void addErrorHandlers(ErrorHandler errorHandler, Method method) {
		this.errorHandlers.put(errorHandler, method);
	}

}
