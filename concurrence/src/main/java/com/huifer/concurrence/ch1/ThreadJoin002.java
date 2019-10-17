package com.huifer.concurrence.ch1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Title : ThreadJoin002 </p>
 * <p>Description : thread join 计算阶乘</p>
 *
 * @author huifer
 * @date 2019-03-27
 */
public class ThreadJoin002 {

    /**
     * 需要计算的值
     */
    public static final List<Long> LONG_LIST = Arrays.asList(1L, 2L, 3L, 4L, 5L);

    public static void main(String[] args) {
        Runnable r = () -> {
            List<FactorialCalculator> threads = new ArrayList<>();
            for (Long aLong : LONG_LIST) {
                FactorialCalculator factorialCalculator = new FactorialCalculator(aLong);
                threads.add(factorialCalculator);
                factorialCalculator.start();
            }
            for (FactorialCalculator thread : threads) {
                try {
                    thread.join();
                    System.out.println(
                            "当前计算值" + thread.getNumber() + " 结果：" + thread.getResult());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t = new Thread(r);
        t.start();

    }

    private static class FactorialCalculator extends Thread {

        private final Long number;
        private BigDecimal result;

        FactorialCalculator(Long number) {
            this.number = number;
        }

        public Long getNumber() {
            return number;
        }

        public BigDecimal getResult() {
            return result;
        }

        @Override
        public void run() {
            result = calc(number);
        }

        private BigDecimal calc(Long number) {
            BigDecimal factorial = BigDecimal.ONE;
            for (int i = 1; i <= number; i++) {
                factorial = factorial.multiply(new BigDecimal(i));
            }
            return factorial;
        }
    }

}
