package com.huifer.rmi.jdk;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * <p>Title : Server </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-11
 */
public class Server {

    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        // 注册中心?
        Registry registry = LocateRegistry.createRegistry(1099);
        Naming.rebind("rmi://192.168.1.215/hello", service);
        System.out.println("服务启动成功");

    }

}
