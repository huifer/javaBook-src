package com.huifer.concurrence.disruptor.quick;

import com.lmax.disruptor.EventHandler;

/**
 * <p>Title : OrderEventHandler </p>
 * <p>Description : 处理类 </p>
 *
 * @author huifer
 * @date 2019-06-04
 */
public class OrderEventHandler implements EventHandler<OrderEvent> {

    @Override
    public void onEvent(OrderEvent orderEvent, long l, boolean b) throws Exception {
        System.out.println(orderEvent);
    }
}
