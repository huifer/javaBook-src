package com.huifer.webflux.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 描述:
 * 自定义校验异常
 *
 * @author huifer
 * @date 2019-06-02
 */
@Getter
@Setter
@NoArgsConstructor
public class StudentException extends RuntimeException {
    private String errField;
    private String errValue;

    public StudentException(String message, String errField, String errValue) {
        super(message);
        this.errField = errField;
        this.errValue = errValue;
    }
}
