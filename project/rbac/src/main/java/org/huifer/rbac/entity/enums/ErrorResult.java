package org.huifer.rbac.entity.enums;

import org.huifer.rbac.entity.res.Result;

public enum ErrorResult {
    INSERT(4001, "插入失败"),
    UPDATE(4001, "更新失败"),
    DELETE(4001, "删除失败"),
    QUERY(4001, "查询失败"),
    COMMON(4001, "通用异常"),
    PARAM_ERROR(4005, "参数错误"),
    MAX_UPLOAD_SIZE(10, "文件大小超过限制"),
    REMOTE_SERVICE_ERROR(6, "资源不存在"),
    ;

    private final int code;

    private final String msg;

    ErrorResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Result to() {
        Result<Object> objectResult = new Result<>();
        objectResult.setCode(this.getCode());
        objectResult.setData(null);
        objectResult.setMsg(this.msg);
        return objectResult;
    }

    public Result to(Object data) {
        Result<Object> objectResult = new Result<>();
        objectResult.setCode(this.getCode());
        objectResult.setData(data);
        objectResult.setMsg(this.msg);
        return objectResult;
    }

}
