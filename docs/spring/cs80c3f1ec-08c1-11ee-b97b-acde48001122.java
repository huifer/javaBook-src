package com.huifer.concurrence.ch2.producer_consumer;

/**
 * <p>Title : Main </p>
 * <p>Description : main</p>
 *
 * @author huifer
 * @date 2019-03-28
 */
public class Main {

    public static void main(String[] args) {
        SharedObject sharedObject = new SharedObject();
        new Producer(sharedObject).start();
        new Consumer(sharedObject).start();
    }
}
