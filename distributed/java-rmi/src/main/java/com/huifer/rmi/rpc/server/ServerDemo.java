package com.huifer.rmi.rpc.server;

import com.huifer.rmi.rpc.HelloService;
import com.huifer.rmi.rpc.HelloServiceImpl;

import java.io.IOException;

/**
 * <p>Title : ServerDemo </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-11
 */
public class ServerDemo {

    public static void main(String[] args) throws IOException {
        HelloService helloService = new HelloServiceImpl();

        RpcServer rpcServer = new RpcServer();
        rpcServer.publisher(helloService, 8888);

    }
}
