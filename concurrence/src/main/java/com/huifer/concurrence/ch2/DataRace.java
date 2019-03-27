package com.huifer.concurrence.ch2;

/**
 * <p>Title : DataRace </p>
 * <p>Description : 数据竞争</p>
 *
 * @author huifer
 * @date 2019-03-26
 */
public class DataRace {

    // 输出运算流程：从0开始累加1
    private static Integer calc = 0;

    public static void main(String[] args) {
        Runnable r = () -> {
            for (int i = 0; i < 5; i++) {
                addOneSynchronized();
            }
        };
        Thread t1 = new Thread(r, "线程01");
        Thread t2 = new Thread(r, "线程02");
        t1.start();
        t2.start();
    }

    private synchronized static void addOneSynchronized() {
        int now = calc;
        calc++;
        System.out.println(Thread.currentThread().getName() + " - 操作前 " + now + " 操作后 " + calc);
    }

    private  static void addOne() {
        int now = calc;
        calc++;
        System.out.println(Thread.currentThread().getName() + " - 操作前 " + now + " 操作后 " + calc);
    }

}
