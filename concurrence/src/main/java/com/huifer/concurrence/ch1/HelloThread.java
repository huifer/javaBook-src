package com.huifer.concurrence.ch1;

/**
 * <p>Title : HelloThread </p>
 * <p>Description : HelloThread</p>
 *
 * @author huifer
 * @date 2019-03-25
 */
public class HelloThread extends Thread {

    public static void main(String[] args) {
        HelloThread th = new HelloThread();
        th.setName("测试线程名称");
        th.start();
        System.out.println("线程名称："+th.getName());
        System.out.println("线程状态：" + th.getState());
        System.out.println("线程优先级：" + th.getPriority());
        System.out.println("是否是守护线程：" + th.isDaemon());
        System.out.println("是否是存活：" + th.isAlive());

    }

    @Override
    public void run() {
        System.out.println("hello Thread");
    }

}
