package com.huifer.jdk.sub.processor;

import java.util.concurrent.Flow;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-06-02
 */
public class SubscrioberForString implements Flow.Subscriber<String> {
    private Flow.Subscription subscription;


    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(10);
    }

    @Override
    public void onNext(String item) {
        System.out.println("当前消息 ： " + item);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("sub Error");
    }

    @Override
    public void onComplete() {
        System.out.println("sub work end");

    }
}
