package com.huifer.zk.rpcserver;

import com.huifer.zk.inter.HelloInInterface;
import com.huifer.zk.inter.HelloInInterfaceImpl;
import com.huifer.zk.regist.MyRegisterCenter;
import com.huifer.zk.regist.MyRegisterCenterImpl;

import java.io.IOException;

/**
 * <p>Title : ZkRpcServerRun </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-13
 */
public class ZkRpcServerRun {

    public static void main(String[] args) throws IOException {
        HelloInInterface hello = new HelloInInterfaceImpl();
        MyRegisterCenter registerCenter = new MyRegisterCenterImpl();

        ZkRpcServer zkRpcServer = new ZkRpcServer(registerCenter, "127.0.0.1:8080");

        zkRpcServer.bind(hello);
        zkRpcServer.publisher();

        System.in.read();
    }

}
