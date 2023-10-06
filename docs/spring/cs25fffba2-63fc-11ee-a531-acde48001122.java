package com.huifer.zk;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * <p>Title : Run </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-12
 */
public class Run {

    public static void main(String[] args) throws IOException {
        CountDownLatch countDownLatch = new CountDownLatch(getAnInt());

        for (int i = 0; i < getAnInt(); i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    DistributedLock distributedLock = new DistributedLock();
                    distributedLock.lock();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "thread - " + i).start();
            countDownLatch.countDown();

        }

        System.in.read();


    }

    private static int getAnInt() {
        return 5;
    }
}
