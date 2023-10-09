package com.huifer.kafka.core.excephandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultExceptionHandler implements ExceptionHandler {


    public boolean support(Throwable t) {
        return true;
    }

    public void handle(Throwable t, String message) {
        if (t instanceof InterruptedException) {
            log.error("Maybe it is shutting down. Or interruped when handing the message:\t" + message, t);
        } else {
            log.error("Failed to handle the message: \t" + message, t);
        }
    }
}
