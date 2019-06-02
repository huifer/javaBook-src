package com.huifer.jdk.sub.processor;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * 消息处理器
 * int 转 string
 *
 * @author huifer
 * @date 2019-06-02
 */
public class Processor extends SubmissionPublisher<String> implements Flow.Processor<Integer, String> {




    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(10);
    }

    @Override
    public void onNext(Integer item) {


        // 具体逻辑
        // 过滤 大于50 的数字
        // 其他的 转换成 string
        if (item < 50) {
            this.submit("处理结果为:" + String.valueOf(item));
        }


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("error");
    }

    @Override
    public void onComplete() {
        System.out.println("work end");
    }
}
