package org.huifer.rbac.exception;

import org.apache.commons.lang3.StringUtils;
import org.huifer.rbac.entity.enums.ErrorResult;

public class ServiceException extends RuntimeException {

    private String code = "";

    private String errorMessage = StringUtils.EMPTY;

    public ServiceException(String errorMessage) {
        this(StringUtils.EMPTY, errorMessage);
    }

    public ServiceException(ErrorResult rc) {
        super();
        this.code = String.valueOf(rc.getCode());

        this.errorMessage = rc.getMsg();
    }

    public ServiceException(String code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}