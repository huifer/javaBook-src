package com.huifer.jenkinsspringboot.controller;

import com.huifer.jenkinsspringboot.entity.RetResponse;
import com.huifer.jenkinsspringboot.entity.RetResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-05
 */
@ControllerAdvice
@ResponseBody
public class AdvController {

    @ExceptionHandler(value = Exception.class)
    public RetResult defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        return RetResponse.makeErrRsp(e.getMessage());
    }

}
