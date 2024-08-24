package com.huifer.zk.rpcserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title : ProcessorHandler </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-11
 */
public class ProcessorHandler implements Runnable {


    private Socket socket;
    private Map<String, Object> serverHandler = new HashMap<>();


    public ProcessorHandler(Socket socket, Map<String, Object> serverHandler) {
        this.socket = socket;
        this.serverHandler = serverHandler;
    }


    @Override
    public void run() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            ZkRpcRequest zkRpcRequest = (ZkRpcRequest) ois.readObject();
            Object o = rpcInvoke(zkRpcRequest);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(o);
            oos.flush();
            oos.close();
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private Object rpcInvoke(ZkRpcRequest zkRpcRequest) throws Exception {
        String className = zkRpcRequest.getClassName();
        String methodName = zkRpcRequest.getMethodName();
        Object[] parameters = zkRpcRequest.getParameters();

        Class<?>[] types = new Class[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            types[i] = parameters[i].getClass();
        }

        Object service = serverHandler.get(zkRpcRequest.getClassName());
        Method method = service.getClass().getMethod(methodName, types);
        return method.invoke(service, parameters);

    }
}
