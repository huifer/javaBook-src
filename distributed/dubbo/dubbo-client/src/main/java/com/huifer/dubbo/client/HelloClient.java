package com.huifer.dubbo.client;

import com.huifer.dubbo.server.api.DubboHello;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Title : HelloClient </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-13
 */
public class HelloClient {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "dubbo-client.xml");
        DubboHello dubboHello = (DubboHello) context.getBean("dubboHello");

        String helloDubbo = dubboHello.hello("hello dubbo");
        System.out.println(helloDubbo);
    }

}
