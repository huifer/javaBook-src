package com.huifer.security.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-11-17
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
