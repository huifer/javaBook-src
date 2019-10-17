package com.huifer.design.strategy;

/**
 * <p>Title : PayState </p>
 * <p>Description : 支付状态</p>
 *
 * @author huifer
 * @date 2019-05-20
 */
public class PayState {

    private int code;
    private Object data;
    private String msg;

    public PayState(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"code\":")
                .append(code);
        sb.append(",\"data\":")
                .append(data);
        sb.append(",\"msg\":\"")
                .append(msg).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
