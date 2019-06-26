package com.huifer.spring.session.springsession.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Title : ResultEnum </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    UNkNOWN_ACCOUNT(1, "用户不存在"),

    PARAM_ERROR(2, "参数不正确"),

    ACCOUNT_EXIST(3, "该账号已存在"),

    USERNAME_OR_PASSWORD_ERROR(4, "用户名或密码错误"),

    ACCOUNT_DISABLE(5, "账号已被禁用"),

    AUTH_ERROR(6, "账户验证失败"),

    NOT_LOGIN(7, "未登录"),

    NOT_PERMSSION(8, "您没有访问该功能的权限");

    private Integer code;

    private String message;
}
