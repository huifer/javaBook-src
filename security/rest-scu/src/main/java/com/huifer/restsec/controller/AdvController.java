package com.huifer.restsec.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-07
 */
@RestControllerAdvice
public class AdvController {

    @ExceptionHandler(Exception.class)
    public Map<String, Object> ex(
            Exception e
    ) {
        HashMap<String, Object> m = new HashMap<>();

        m.put("error", e);

        return m;

    }
}
