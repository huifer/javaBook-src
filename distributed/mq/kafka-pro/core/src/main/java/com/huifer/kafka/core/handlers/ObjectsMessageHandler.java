package com.huifer.kafka.core.handlers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.huifer.kafka.core.excephandler.ExceptionHandler;

import java.util.List;

public abstract class ObjectsMessageHandler<T> extends SafelyMessageHandler {

	public ObjectsMessageHandler() {
		super();
	}

	public ObjectsMessageHandler(ExceptionHandler exceptionHandler) {
		super(exceptionHandler);
	}

	public ObjectsMessageHandler(List<ExceptionHandler> exceptionHandlers) {
		super(exceptionHandlers);
	}

	protected void doExecute(String message) {
		JSONArray jsonArray = JSON.parseArray(message);
		doExecuteObjects(jsonArray);
	}

	protected abstract void doExecuteObjects(JSONArray jsonArray);
}
