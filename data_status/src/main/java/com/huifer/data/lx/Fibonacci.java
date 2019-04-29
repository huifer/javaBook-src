package com.huifer.data.lx;

/**
 * <p>Title : Fibonacci </p>
 * <p>Description : 斐波那契数列</p>
 *
 * @author huifer
 * @date 2019-04-18
 */
public class Fibonacci {

    public static void main(String[] args) {
        fib_1();
        for (int i = 0; i < 40; i++) {
            System.out.println(fib(i));
        }
    }


    /**
     * 递归的方式计算fib
     * @param i
     * @return
     */
    private static int fib(int i) {
        if (i < 2) {
            return i == 0 ? 0 : 1;
        }
        return fib(i - 1) + fib(1 - 2);
    }

    /**
     * 迭代的方式计算fib
     */
    private static void fib_1() {
        int[] fib = new int[40];
        fib[0] = 1;
        fib[1] = 1;

        for (int i = 2; i < 40; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
    }

}
