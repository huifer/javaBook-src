package com.huifer.jdk.sub;

import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * 订阅测试
 *
 * @author huifer
 * @date 2019-06-02
 */
public class SubTest {


    /***
     * 订阅者 和 发布者 是两个独立的线程
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws InterruptedException {

        // 创建发布者
        SubmissionPublisher<Integer> sp = new SubmissionPublisher<>();
        // 创建订阅者
        SomeSubscriber subscriber = new SomeSubscriber();
        // 建立订阅关系
        sp.subscribe(subscriber);
        // 发布者生产数据
        for (int i = 0; i < 300; i++) {
            System.out.println("发布者线程 " + Thread.currentThread().getName());
            System.out.println("生产了\t" + i + " \t条数据");
            sp.submit(i);
        }

        // 订阅者接收数据

        // 关闭发布者
        sp.close();

        // 防止发布者消息生产结束 ， 消费者被结束
        TimeUnit.SECONDS.sleep(20);

    }
}
