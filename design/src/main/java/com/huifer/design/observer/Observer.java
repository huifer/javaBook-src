package com.huifer.design.observer;

/**
 * <p>Title : Observer </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-21
 */
public class Observer {

    public void advice(Event event) {

        System.out.println("向总部报告");
        System.out.println(event);

    }

}
