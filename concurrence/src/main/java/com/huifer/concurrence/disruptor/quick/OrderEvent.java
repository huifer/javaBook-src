package com.huifer.concurrence.disruptor.quick;

import java.io.Serializable;

/**
 * <p>Title : OrderEvent </p>
 * <p>Description : 订单</p>
 *
 * @author huifer
 * @date 2019-06-04
 */
public class OrderEvent implements Serializable {


    private static final long serialVersionUID = 4026526345323124428L;
    private int value;

    public OrderEvent() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"value\":")
                .append(value);
        sb.append('}');
        return sb.toString();
    }
}
