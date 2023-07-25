package com.huifer.design.adapter.login;

/**
 * <p>Title : ResultMsg </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-21
 */
public class ResultMsg {

    private int code;
    private Object data;
    private String id;

    public ResultMsg(int code, Object data, String id) {
        this.code = code;
        this.data = data;
        this.id = id;
    }

    public ResultMsg() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"code\":")
                .append(code);
        sb.append(",\"data\":")
                .append(data);
        sb.append(",\"id\":\"")
                .append(id).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
