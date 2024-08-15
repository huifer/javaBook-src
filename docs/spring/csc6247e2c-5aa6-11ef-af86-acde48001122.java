package com.huifer.design.observer;

import java.lang.reflect.Method;

/**
 * <p>Title : Testing </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-21
 */
public class Testing {

    public static void main(String[] args) throws Exception {
        Observer observer = new Observer();
        Subject subject = new Subject();
        // 观察者的具体方法
        Method advice = Observer.class.getMethod("advice", Event.class);

        // 监控小偷的行为
        subject.moveLisenter(
                SubjectEventType.ON_MOVE, observer, advice
        );
        subject.move();


    }

}
