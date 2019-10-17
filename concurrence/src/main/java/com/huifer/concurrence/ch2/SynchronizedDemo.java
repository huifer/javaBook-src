package com.huifer.concurrence.ch2;

/**
 * <p>Title : SynchronizedDemo </p>
 * <p>Description : 同步</p>
 *
 * @author huifer
 * @date 2019-03-26
 */
public class SynchronizedDemo {

    private static Integer mode = 1;
    private static Integer result = 0;

    public static void main(String[] args) {
        Id id = new Id();
        System.out.println(id.getCount());

        Runnable r1 = () -> {
            synchronized (mode) {
                result = mode + 2;
                System.out.println("当前reslut " + result);
            }
        };

        Thread thread1 = new Thread(r1);
        thread1.start();

    }
}

class Id {

    private static int count = 1;

    public synchronized int getCount() {
        return ++count;
    }
}
