package com.huifer.jdk.sub.processor;

import java.util.Random;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-06-02
 */
public class ProcessorTest {
    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        Processor processor = new Processor();
        SubscrioberForString subscrioberForString = new SubscrioberForString();


        publisher.subscribe(processor);

        processor.subscribe(subscrioberForString);


        for (int i = 0; i < 300; i++) {
            int i1 = new Random().nextInt(100);
            publisher.submit(i1);
        }
        TimeUnit.SECONDS.sleep(500);
        System.out.println("main work end");
    }
}
