package com.huifer.dubbo.server.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * <p>Title : Bootstrap </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-13
 */
public class Bootstrap {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "META-INF/spring/dubbo-server.xml");
        context.start();
        System.in.read();
    }

}
