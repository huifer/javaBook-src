package com.huifer.kafka.core.handlers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huifer.kafka.core.excephandler.ExceptionHandler;

import java.util.List;

public abstract class ObjectMessageHandler<T> extends SafelyMessageHandler{


	public ObjectMessageHandler() {
		super();
	}

	public ObjectMessageHandler(ExceptionHandler exceptionHandler) {
		super(exceptionHandler);
	}

	public ObjectMessageHandler(List<ExceptionHandler> exceptionHandlers) {
		super(exceptionHandlers);
	}

	protected void doExecute(String message) {
		JSONObject jsonObject = JSON.parseObject(message);
		doExecuteObject(jsonObject);
	}

	protected abstract void doExecuteObject(JSONObject jsonObject);
}