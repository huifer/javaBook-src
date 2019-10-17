package com.huifer.concurrence.issues;

/**
 * Thread run 和 start 方法的区别
 */
public class StartAndRunDiff {

    public static void main(String[] args) {

        TestThread testThread = new TestThread();
        // run()作为一个方法进行调用,依附于主线程main
        testThread.run();
        System.out.println("============");
        // start() 方法调用Thread.start()
        // 在start方法中进行了 ThreadGroup 操作, 在此时创建了一个新的线程
        // 如何证明?Thread.getAllStackTraces()
        testThread.start();
        System.out.println("end");

    }


    static class TestThread extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().toString());
            super.run();
        }
    }
}

