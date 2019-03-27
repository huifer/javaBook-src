package com.huifer.concurrence.ch2;

/**
 * <p>Title : FairnessDemo </p>
 * <p>Description : FairnessDemo</p>
 *
 * @author huifer
 * @date 2019-03-27
 */
public class FairnessDemo {
    private static Object sharedObj = new Object();

    public static void main (String[] args) {

        for (int i = 0; i < 5; i++) {
            Fairness fairness = new Fairness();
            fairness.start();
        }
    }


    private static class Fairness extends Thread {
        @Override
        public void run () {

            int c = 0;
            while (true) {
                synchronized (sharedObj) {
                    if (c == 10) {
                        c = 0;
                    }
                    ++c;
                    System.out.println(Thread.currentThread().getName() + " 当前C值 ：" + c);
                    try {
                        sharedObj.wait(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
