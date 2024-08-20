package com.huifer.rmi.jdk;

import java.rmi.Naming;

/**
 * <p>Title : Client </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-11
 */
public class Client {

    public static void main(String[] args) throws Exception {
        HelloService helloService = (HelloService) Naming.lookup("rmi://localhost/hello");
        String msg = helloService.hello("msg");
        System.out.println(msg);
    }

}
