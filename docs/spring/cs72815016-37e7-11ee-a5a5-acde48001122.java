package com.huifer.concurrence.ch1;

/**
 * <p>Title : HelloRunnable </p>
 * <p>Description : Runnable </p>
 *
 * @author huifer
 * @date 2019-03-25
 */
public class HelloRunnable {

    public static void main(String[] args) {
        Runnable runnable = runnableDemo();
        Thread t = new Thread(runnable, "测试线程");
        t.start();
        System.out.println(t.getName());

    }

    private static Runnable runnableDemo() {
        Runnable r = () -> System.out.println("hello runnable");
        return r;
    }
}
