package com.huifer.bus.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.EventListener;

/**
 * <p>Title : SpringAnnotationEvent </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-03
 */
public class SpringAnnotationEvent {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringAnnotationEvent.class);

        context.refresh();
        context.publishEvent(new MyEvent("hello "));

        context.close();

    }

    @EventListener
    public void onMessage(MyEvent event) {
        System.out.println("监听到事件 " + event.getSource());
    }

    private static class MyEvent extends ApplicationEvent {

        public MyEvent(Object source) {
            super(source);
        }
    }

}
