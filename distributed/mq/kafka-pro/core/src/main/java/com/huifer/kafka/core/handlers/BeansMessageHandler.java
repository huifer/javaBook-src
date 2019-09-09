package com.huifer.kafka.core.handlers;

import com.alibaba.fastjson.JSON;
import com.huifer.kafka.core.excephandler.ExceptionHandler;

import java.util.List;

public abstract class BeansMessageHandler<T> extends SafelyMessageHandler {
    private Class<T> clazz;

    public BeansMessageHandler(Class<T> clazz) {
        super();

        this.clazz = clazz;
    }

    public BeansMessageHandler(Class<T> clazz, ExceptionHandler exceptionHandler) {
        super(exceptionHandler);

        this.clazz = clazz;
    }

    public BeansMessageHandler(Class<T> clazz, List<ExceptionHandler> exceptionHandlers) {
        super(exceptionHandlers);

        this.clazz = clazz;
    }

    protected void doExecute(String message) {
        List<T> beans = JSON.parseArray(message, clazz);
        doExecuteBeans(beans);
    }

    protected abstract void doExecuteBeans(List<T> bean);
}
