package com.huifer.concurrence.ch2.producer_consumer;

/**
 * <p>Title : SharedObject </p>
 * <p>Description :共享类 </p>
 *
 * @author huifer
 * @date 2019-03-28
 */
public class SharedObject {

    private char aChar;
    private volatile boolean writed = true;

    public synchronized char getaChar() {
        while (writed) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.writed = true;
        notify();
        return this.aChar;
    }

    public synchronized void setaChar(char aChar) {
        while (!writed) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.aChar = aChar;
        this.writed = false;
        notify();
    }
}
