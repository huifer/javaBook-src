package com.huifer.spring.session.springsession.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Title : ForbiddenEnum </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */@AllArgsConstructor
@Getter
public enum ForbiddenEnum {
    ENABLE(0,"启用"),

    DISABLE(1,"禁用");

    private Integer code;

    private String message;

}
