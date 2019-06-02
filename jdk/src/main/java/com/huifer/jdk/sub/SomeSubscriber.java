package com.huifer.jdk.sub;


import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * 订阅者
 *
 * @author huifer
 * @date 2019-06-02
 */
public class SomeSubscriber implements Flow.Subscriber {

    private Flow.Subscription subscription;


    /**
     * 发布者第一次发送消息调用
     *
     * @param subscription
     */
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;

        // 设置订阅者 （首次） 向发布者订阅多少信息量
        this.subscription.request(10);

    }

    /**
     * 接收一次执行一次
     *
     * @param item
     */
    @Override
    public void onNext(Object item) {
        System.out.println("当前消费信息为" + item.toString());
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 设置订阅者 向发布者订阅多少信息量 ， 消费一条 在订阅10条
        this.subscription.request(10);

//        if () { // 什么情况下取消订阅
//            this.subscription.cancel();
//        }

    }


    /**
     * 订阅过程中的异常处理
     *
     * @param throwable
     */
    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        this.subscription.getClass();
    }


    /**
     * 令牌中的消息消费结束
     */
    @Override
    public void onComplete() {
        System.out.println("订阅者线程 " + Thread.currentThread().getName());
        System.out.println("令牌中不在存在内容");
    }
}
