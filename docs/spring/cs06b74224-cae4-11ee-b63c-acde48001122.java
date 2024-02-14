package com.huifer.concurrence.ch2.communication;

/**
 * <p>Title : EZdemo </p>
 * <p>Description : 最简单的通讯</p>
 *
 * @author huifer
 * @date 2019-03-27
 */
public class EZdemo {

    private static String message;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (message == null) {
                // 因为线程去启动顺序是不固定的所以需要等待复制成功
                System.out.println("还没有值");
            }
            System.out.println("获取通讯变量" + message);
        });
        Thread thread2 = new Thread(() -> {
            message = " 初始化变量";

        });
        thread1.start();
        thread2.start();
    }

}
