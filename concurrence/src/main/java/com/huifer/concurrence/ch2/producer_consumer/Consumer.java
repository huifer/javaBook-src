package com.huifer.concurrence.ch2.producer_consumer;

/**
 * <p>Title : Consumer </p>
 * <p>Description : 消费者</p>
 *
 * @author huifer
 * @date 2019-03-28
 */
public class Consumer extends Thread {

    private final SharedObject sharedObject;

    public Consumer(SharedObject sharedObject) {
        this.sharedObject = sharedObject;
    }

    @Override
    public void run() {
        char ch;
        do {
            synchronized (sharedObject) {

                ch = sharedObject.getaChar();
                System.out.println("消费者取出数据 " + ch);
            }
        } while (ch != 'f');
    }
}
