package com.huifer.concurrence.disruptor.quick;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Title : Test </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-04
 */
public class Test {

    public static void main(String[] args) {
        OrderEventFactory orderEventFactory = new OrderEventFactory();
        int ringBufferSize = 1024 * 1024;
        ExecutorService service = Executors
                .newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        /**
         * com.lmax.disruptor.dsl.Disruptor#Disruptor(com.lmax.disruptor.EventFactory, int, java.util.concurrent.Executor, com.lmax.disruptor.dsl.ProducerType, com.lmax.disruptor.WaitStrategy) 参数说明
         * 1. orderEventFactory: 创建event 消息工厂
         * 2. ringBufferSize: 容器大小
         * 3. service: 线程池
         * 4. ProducerType: 单生产模式，多生产模式
         * 5. waitStrategy: 等待策略
         */
        // 步骤1：Disruptor实例创建
        Disruptor<OrderEvent> disruptor = new Disruptor<>(
                orderEventFactory,
                ringBufferSize,
                service,
                ProducerType.SINGLE,
                new BlockingWaitStrategy()
        );
        // 步骤2：添加消费者监听器
        disruptor.handleEventsWith(new OrderEventHandler());
        disruptor.start();
        // 步骤3: 数据生产者
        // 获取存储数据的实例
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.addGatingSequences();

        OrderEventProducer producer = new OrderEventProducer(ringBuffer);

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (int i = 0; i < 100; i++) {
            byteBuffer.putInt(0, i);
            producer.send(byteBuffer);
        }

        disruptor.shutdown();
        service.shutdown();

    }

}
