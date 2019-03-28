package com.huifer.concurrence.ch2.communication;

/**
 * <p>Title : WaitNotifyDemo </p>
 * <p>Description : 线程通讯</p>
 *
 * @author huifer
 * @date 2019-03-27
 */
public class WaitNotifyDemo {

    public static void main(String[] args) {
        SharedObject shareObject = new SharedObject();
        new Thread(() -> {
            shareObject.setMessage("我再设置内容");
        }).start();

        new Thread(() -> {
            String message = shareObject.getMessage();
            System.out.println(message);
        }).start();
    }


    private static class SharedObject {
        private String message;

        public synchronized void setMessage (String message) {
            this.message = message;
            notify();
        }

        public synchronized String getMessage () {
            while (message == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return message;
        }
    }

}
