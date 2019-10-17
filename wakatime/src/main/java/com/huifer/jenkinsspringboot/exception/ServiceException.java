package com.huifer.jenkinsspringboot.exception;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-05
 */
public class ServiceException extends RuntimeException {
    public static final ServiceException DATA_ERRO = new ServiceException("没有数据");

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
