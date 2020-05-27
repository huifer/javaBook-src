package org.huifer.rbac.entity.res;

import java.util.Objects;

public class Result<T> {
    private String msg;

    private Integer code;

    private T data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result<?> result = (Result<?>) o;
        return Objects.equals(msg, result.msg) &&
                Objects.equals(code, result.code) &&
                Objects.equals(data, result.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(msg, code, data);
    }

    public Result() {
    }

    public Result(String msg, Integer code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }
}
