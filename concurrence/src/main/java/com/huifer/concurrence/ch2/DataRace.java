package com.huifer.concurrence.ch2;

/**
 * <p>Title : DataRace </p>
 * <p>Description : 数据竞争</p>
 *
 * @author huifer
 * @date 2019-03-26
 */
public class DataRace {

    public static Integer i;

    public  static void main(String[] args) {
        Runnable r = () -> {
            if (i == null) {
                i = 1;
                System.out.println(Thread.currentThread().getName() + "i初始化");
            } else {
                System.out.println(Thread.currentThread().getName() + " i = " + i);
            }
        };

            Thread t = new Thread(r, "thread-1");
            t.start();
            Thread t1 = new Thread(r, "thread-2");
            t1.start();
    }


}
