package com.huifer.concurrence.ch1;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title : ThreadYield </p>
 * <p>Description : yield</p>
 *
 * @author huifer
 * @date 2019-03-27
 */
public class ThreadYield {

    public static void main(String[] args) {
        Task task1 = new Task(true);
        Task task2 = new Task(false);
        new Thread(task1).start();
        new Thread(task2).start();
    }


    private static class Task implements Runnable {

        private final boolean isYield;
        private List<String> stringList = new ArrayList<>();

        public Task(boolean isYield) {
            this.isYield = isYield;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + " start:");
            for (int i = 0; i < 1000000; i++) {
                if (isYield) {
                    stringList.add("NO." + i);
                    Thread.yield();
                }
            }
            System.out.println(name + " end:");

        }
    }


}
