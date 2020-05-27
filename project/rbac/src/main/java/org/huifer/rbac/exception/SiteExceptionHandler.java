package org.huifer.rbac.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.huifer.rbac.entity.enums.ErrorResult;
import org.huifer.rbac.entity.res.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class SiteExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(SiteExceptionHandler.class);

    @Autowired
    private HttpServletRequest httpServletRequest;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result doExceptionHandler(Exception e) {
        Integer code;
        String msg;
        if (e instanceof ServiceException) {
            ServiceException se = (ServiceException) e;
            code = ErrorResult.COMMON.getCode();
            msg = se.getErrorMessage();

            if (StringUtils.isNotBlank(se.getCode())) {
                if (StringUtils.isNumeric(se.getCode())) {
                    code = NumberUtils.toInt(se.getCode());
                }
                else {
                    msg = "[" + se.getCode() + "] " + msg;
                }
            }
        }
        else if (e instanceof MethodArgumentNotValidException) {
            code = ErrorResult.PARAM_ERROR.getCode();
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            msg = StringUtils
                    .defaultIfBlank(ex.getBindingResult().getFieldError().getDefaultMessage(),
                            ErrorResult.PARAM_ERROR.getMsg() + ":" + ex.getBindingResult()
                                    .getFieldError().getField());
        }
        else if (e instanceof MaxUploadSizeExceededException) {
            code = ErrorResult.MAX_UPLOAD_SIZE.getCode();
            msg = ErrorResult.MAX_UPLOAD_SIZE.getMsg();
        }
        else if (e instanceof NoHandlerFoundException) {
            code = ErrorResult.REMOTE_SERVICE_ERROR.getCode();
            msg = StringUtils
                    .defaultIfBlank(e.getMessage(), ErrorResult.REMOTE_SERVICE_ERROR.getMsg());
        }
        else {
            code = ErrorResult.COMMON.getCode();
            msg = StringUtils.defaultIfBlank(e.getMessage(), ErrorResult.COMMON.getMsg());
        }
        LOG.error(
                "url=" + (httpServletRequest != null ? httpServletRequest.getRequestURI() : "内部调用")
                        + ", code=" + code + ", msg=" + msg, e);

        return new Result(msg, code, null);
    }
}