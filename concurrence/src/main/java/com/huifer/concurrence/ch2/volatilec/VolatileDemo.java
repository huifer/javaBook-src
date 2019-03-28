package com.huifer.concurrence.ch2.volatilec;

/**
 * <p>Title : VolatileDemo </p>
 * <p>Description : 交替读写</p>
 *
 * @author huifer
 * @date 2019-03-28
 */
public class VolatileDemo {

    public static volatile int count;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            int tem = 0;
            while (true) {
                if (tem != count) {
                    tem = count;
                    System.out.println("读取当前值 ：" + count);

                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                count++;
                System.out.println("修改当前值 ：" + count);
                try {
                    Thread.sleep(5);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            // 退出
            System.exit(0);
        });
        t1.start();
        t2.start();
    }

}
