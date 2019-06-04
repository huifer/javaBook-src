package com.huifer.concurrence.disruptor.quick;

import com.lmax.disruptor.RingBuffer;
import java.nio.ByteBuffer;

/**
 * <p>Title : OrderEventProducer </p>
 * <p>Description : 订单生产者</p>
 *
 * @author huifer
 * @date 2019-06-04
 */
public class OrderEventProducer {

    private RingBuffer<OrderEvent> ringBuffer;

    public OrderEventProducer(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void send(ByteBuffer byteBuffer) {
        //1.从RingBuffer中获取可用序号
        long sequence = ringBuffer.next();
        try {
            //2. 根据sequence 需要找到一个没有数据内容的 Event
            OrderEvent orderEvent = ringBuffer.get(sequence);
            //3. 填充数据
            orderEvent.setValue(byteBuffer.getInt(0));
        } finally {
            //4. 提交数据
            ringBuffer.publish(sequence);
        }

    }

}
