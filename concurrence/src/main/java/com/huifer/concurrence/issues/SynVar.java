package com.huifer.concurrence.issues;

/**
 * 线程间数据共享
 */
public class SynVar {

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        bank.setMoney(100);
        bank.setName("fur");

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                bank.addMoney(10);
                System.out.println("add当前money" + bank.getMoney());
            }
        }).start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                bank.subMoney(10);
                System.out.println("sub当前money" + bank.getMoney());
            }
        }).start();

        Thread.sleep(1000L);
        System.out.println("======");
        System.out.println(bank.getMoney());
    }

    static class Bank {
        private String name;
        private int money;


        public synchronized void addMoney(int m) {
            this.money += m;
        }

        public synchronized void subMoney(int m) {
            this.money -= m;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }
    }

}

