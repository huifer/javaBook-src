package com.huifer.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

/**
 * <p>Title : ApplicationEventDemo </p>
 * <p>Description : Spring 事件监听</p>
 *
 * @author huifer
 * @date 2019-05-22
 */
public class ApplicationEventDemo {

    public static void main(String[] args) {

        ApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
        // 监听事件
        multicaster.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println("事件:" + event.getSource());
            }
        });

        // 发布事件
        multicaster.multicastEvent(new MyEvent("java"));
        multicaster.multicastEvent(new PayloadApplicationEvent<Object>("python", "python"));
    }

    private static class MyEvent extends ApplicationEvent {

        public MyEvent(Object source) {
            super(source);
        }
    }


}
