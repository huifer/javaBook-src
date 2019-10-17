package com.huifer.kafka.core.handlers;

import com.alibaba.fastjson.JSON;
import com.huifer.kafka.core.excephandler.ExceptionHandler;

import java.util.List;

public abstract class BeanMessageHandler<T> extends SafelyMessageHandler {

    private Class<T> clazz;

    public BeanMessageHandler(Class<T> clazz) {
        super();

        this.clazz = clazz;
    }

    public BeanMessageHandler(Class<T> clazz, ExceptionHandler exceptionHandler) {
        super(exceptionHandler);

        this.clazz = clazz;
    }

    public BeanMessageHandler(Class<T> clazz, List<ExceptionHandler> exceptionHandlers) {
        super(exceptionHandlers);

        this.clazz = clazz;
    }

    protected void doExecute(String message) {
        T bean = JSON.parseObject(message, clazz);
        doExecuteBean(bean);
    }

    protected abstract void doExecuteBean(T bean);
}
