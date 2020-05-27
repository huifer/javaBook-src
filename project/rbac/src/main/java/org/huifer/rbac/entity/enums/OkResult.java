package org.huifer.rbac.entity.enums;

import org.huifer.rbac.entity.res.Result;

public enum OkResult {
    INSERT(200, "插入成功"),
    UPDATE(200, "更新成功"),
    DELETE(200, "删除成功"),
    QUERY(200, "查询成功"),
    ;

    private final int code;

    private final String msg;

    OkResult(int code, String msg) {
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
        Result objectResult = new Result<>();
        objectResult.setCode(this.getCode());
        objectResult.setData(null);
        objectResult.setMsg(this.msg);
        return objectResult;
    }

    public Result to(Object data) {
        Result objectResult = new Result<>();
        objectResult.setCode(this.getCode());
        objectResult.setData(data);
        objectResult.setMsg(this.msg);
        return objectResult;
    }

}
