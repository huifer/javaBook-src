package com.huifer.concurrence.ch2.livelock;


/**
 * <p>Title : Apple </p>
 * <p>Description : 苹果</p>
 *
 * @author huifer
 * @date 2019-03-27
 */
public class Apple {

    /**
     * 当前所有者
     */
    private People owner;

    public Apple(People d) {
        owner = d;
    }

    public People getOwner() {
        return owner;
    }

    public synchronized void setOwner(People d) {
        owner = d;
    }
}
