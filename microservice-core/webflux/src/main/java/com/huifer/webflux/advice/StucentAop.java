package com.huifer.webflux.advice;

import com.huifer.webflux.exception.StudentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-06-02
 */
@ControllerAdvice
public class StucentAop {

    private static String apply(String s1, String s2) {
        return s1 + "\n" + s2;
    }

    @ExceptionHandler
    public ResponseEntity validateHandleName(StudentException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity validateHandle(WebExchangeBindException ex) {
        return new ResponseEntity(ex2str(ex), HttpStatus.BAD_REQUEST);
    }

    private String ex2str(WebExchangeBindException ex) {

        return ex.getFieldErrors().stream().map(
                e -> {
                    return e.getField() + " : " + e.getDefaultMessage();
                }
        ).reduce("", StucentAop::apply);


    }


}
