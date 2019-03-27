package com.huifer.concurrence.ch1;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title : ThreadDemo002 </p>
 * <p>Description : 守护线程输出当前内存使用</p>
 *
 * @author huifer
 * @date 2019-03-26
 */
public class ThreadDemo002 {

    public static void main(String[] args) {
        MemoryWatcherThread.start();

    }
}


class MemoryWatcherThread implements Runnable {

    public static void start() {
        Thread thread = new Thread(new MemoryWatcherThread());
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.setDaemon(true);
        thread.start();
        List<String> s = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            s.add("i" + i);
        }
        System.out.println("end");
    }

    @Override
    public void run() {
        long memoryUsed = getMemoryUsed();
        System.out.println("内存使用" + memoryUsed + " MB");
        while (true) {
            long memoryUsed1 = getMemoryUsed();
            if (memoryUsed != memoryUsed1) {
                memoryUsed = memoryUsed1;
                System.out.println("内存使用" + memoryUsed + " MB");
            }
        }
    }

    private long getMemoryUsed() {
        return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024
                / 1024;
    }
}
