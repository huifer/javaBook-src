package com.huifer.design.observer;

/**
 * <p>Title : Subject </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-21
 */
public class Subject extends EventLisenter {


    public void move() {
        System.out.println("小偷移动");
        trigger(SubjectEventType.ON_MOVE);
    }

}
