package com.huifer.concurrence.ch2.livelock;

/**
 * <p>Title : LiveLock </p>
 * <p>Description : 活锁</p>
 *
 * @author huifer
 * @date 2019-03-27
 */
public class LiveLock {

    public static void main(String[] args) {
        final People zhansan = new People("张三", true);
        final People lisi = new People("李四", true);

        Apple s = new Apple(zhansan);
        System.out.println("最开始把苹果给张三");
        System.out.println(s.getOwner());
        new Thread(() -> {
            zhansan.doWork(s, lisi);
        }).start();

        new Thread(() -> {
            lisi.doWork(s, zhansan);
        }).start();

    }

}
