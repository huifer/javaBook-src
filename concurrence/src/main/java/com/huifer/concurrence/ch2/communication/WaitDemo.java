package com.huifer.concurrence.ch2.communication;

/**
 * <p>Title : WaitDemo </p>
 * <p>Description : wait 通讯</p>
 *
 * @author huifer
 * @date 2019-03-27
 */
public class WaitDemo {

    private static String message;

    public static void main(String[] args) {
        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {

                while (message == null) {
                    // 因为线程去启动顺序是不固定的所以需要等待复制成功
                    System.out.println("还没有值");
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("获取通讯变量" + message);
        });
        Thread thread2 = new Thread(() -> {
            synchronized (lock) {

                message = " 初始化变量";
                lock.notify();

            }

        });

        thread1.start();
        thread2.start();

    }

}
