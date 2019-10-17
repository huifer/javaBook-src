package com.huifer.concurrence.ch4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p>Title : HelloExecutor </p>
 * <p>Description : executor</p>
 *
 * @author huifer
 * @date 2019-03-28
 */
public class HelloExecutor {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<?> hello_executor = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello executor");
            }
        });
        executorService.shutdown();
    }

}
