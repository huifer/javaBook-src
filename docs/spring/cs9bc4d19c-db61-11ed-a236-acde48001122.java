package com.huifer.concurrence.ch2;

/**
 * <p>Title : StarveDemo </p>
 * <p>Description : 饿死</p>
 *
 * @author huifer
 * @date 2019-03-27
 */
public class StarveDemo {

    private static Object sharedObj = new Object();

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            StarveThread progressThread = new StarveThread("线程-" + i);
            progressThread.start();
        }

    }


    private static class StarveThread extends Thread {

        public StarveThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            int c = 0;
            while (true) {
                synchronized (sharedObj) {
                    if (c == 10) {
                        c = 0;
                    }
                    ++c;
                    System.out.println(Thread.currentThread().getName() + "值:" + c);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
