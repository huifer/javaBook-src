package com.huifer.zk.rpcserver;

import com.huifer.zk.regist.MyRegisterCenter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Title : ZkRpcServer </p>
 * <p>Description : Rpc服务 </p>
 *
 * @author huifer
 * @date 2019-06-13
 */
public class ZkRpcServer {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();
    /**
     * 注册中心
     */
    private MyRegisterCenter registerCenter;

    /**
     * 服务发布地址
     */
    private String serviceAddress;

    /**
     * serverName -> serverObject
     */
    private Map<String, Object> serverHandler = new HashMap<>();


    public ZkRpcServer(MyRegisterCenter registerCenter, String serviceAddress) {
        this.registerCenter = registerCenter;
        this.serviceAddress = serviceAddress;
    }

    /**
     * 服务名称和服务对象关系创建
     *
     * @param services 服务
     */
    public void bind(Object... services) {
        for (Object service : services) {
            // 获取RpcAnnotation注解信息
            RpcAnnotation annotation = service.getClass().getAnnotation(RpcAnnotation.class);
            String serviceName = annotation.value().getName();
            serverHandler.put(serviceName, service);
        }
    }

    public void publisher() throws IOException {
        ServerSocket serverSocket = null;

        try {
            // 拆分192.168.1.1:8888
            String[] addres = serviceAddress.split(":");
            serverSocket = new ServerSocket(Integer.parseInt(addres[1]));

            for (String interfaceName : serverHandler.keySet()) {
                registerCenter.register(interfaceName, serviceAddress);
                System.out.println("ZkRpcServer 服务注册成功" + interfaceName + "-->" + serviceAddress);
            }

            while (true) {
                Socket accept = serverSocket.accept();
                EXECUTOR_SERVICE.execute(new ProcessorHandler(accept, serverHandler));
            }




        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }



    }


}
