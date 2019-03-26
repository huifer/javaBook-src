package com.huifer.concurrence.ch2;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title : DeadLockDemo </p>
 * <p>Description : 死锁</p>
 *
 * @author huifer
 * @date 2019-03-26
 */
public class DeadLockDemo {

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public static void main(String[] args) {
        DeadLockDemo deadlockDemo = new DeadLockDemo();
        Runnable r1 = () -> {
            while (true) {
                deadlockDemo.insMethod1();
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable r2 = () -> {
            while (true) {
                deadlockDemo.insMethod2();
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(r1, "线程1");
        Thread t2 = new Thread(r2, "线程2");
        t1.start();
        t2.start();
        // 在这个案例中
        // 线程1获取lock1
        // lock1进入临界区 ,lock2 没有进入
        // 线程2获取lock2
        // lock2进入临界区 ,lock1没有进入
        // JVM同步特性,线程1持有lock1锁 线程2持有lock2锁,线程1需要执行lock2的锁(线程2持有),线程2同理
        // 运行结果交替输出 在方法1中，在方法2中

    }

    public void insMethod1() {
        synchronized (lock1) {
            synchronized (lock2) {
                System.out.println("在方法1中");
            }
        }
    }

    public void insMethod2() {
        synchronized (lock2) {
            synchronized (lock1) {
                System.out.println("在方法2中");
            }
        }
    }
}
