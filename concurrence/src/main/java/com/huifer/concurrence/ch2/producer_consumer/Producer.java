package com.huifer.concurrence.ch2.producer_consumer;

/**
 * <p>Title : Producer </p>
 * <p>Description : 生产者</p>
 *
 * @author huifer
 * @date 2019-03-28
 */
public class Producer extends Thread {

    private final SharedObject sharedObject;

    public Producer(SharedObject sharedObject) {
        this.sharedObject = sharedObject;
    }

    @Override
    public void run() {
        for (char i = 'a'; i <= 'f'; i++) {

            synchronized (sharedObject) {

                sharedObject.setaChar(i);
                System.out.println("生产数据字符  ：" + i);
            }
        }
    }
}
