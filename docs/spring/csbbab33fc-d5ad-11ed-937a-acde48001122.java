package com.huifer.design.singleton.nw;

public class ExecutorThread implements Runnable {

    @Override
    public void run() {
        SimpleSingleton instance = SimpleSingleton.getInstance01();
        System.out.println("当前线程 " + Thread.currentThread().getName() + ",当前对象" + instance);
    }
}
