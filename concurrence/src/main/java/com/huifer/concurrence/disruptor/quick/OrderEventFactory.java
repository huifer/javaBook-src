package com.huifer.concurrence.disruptor.quick;

import com.lmax.disruptor.EventFactory;

/**
 * <p>Title : OrderEventFactory </p>
 * <p>Description : 订单工程</p>
 *
 * @author huifer
 * @date 2019-06-04
 */
public class OrderEventFactory implements EventFactory {

    @Override
    public Object newInstance() {
        return new OrderEvent();
    }
}
