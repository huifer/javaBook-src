package com.huifer.hystrix.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.TimeoutException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>Title : TimeoutController </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-30
 */
@RestControllerAdvice(assignableTypes = {MyHystrixController.class})
public class TimeoutController {


    @ExceptionHandler
    public void onTimeoutException(TimeoutException timeoutException, Writer writer)
            throws IOException {
        writer.write(s(""));

    }

    private String s(String mse) {
        return "error " + mse;
    }

}
