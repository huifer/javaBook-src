package com.huifer.rmi.rpc.server;

import com.huifer.rmi.rpc.client.RpcRequest;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * <p>Title : ProcessorHandler </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-11
 */
public class ProcessorHandler implements Runnable {


    private Socket socket;
    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    public void run() {
        // 请求处理
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) ois.readObject();
            Object o = rpcInvoke(rpcRequest);

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

    /**
     * 将rpcRequest 中数据读取 ， 通过反射获取结果
     * @param rpcRequest rpcRequest {@link RpcRequest}
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private Object rpcInvoke(RpcRequest rpcRequest)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String className = rpcRequest.getClassName();
        String methodName = rpcRequest.getMethodName();
        Object[] parameters = rpcRequest.getParameters();

        Class<?>[] types = new Class[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            types[i] = parameters[i].getClass();
        }

        Method method = service.getClass().getMethod(methodName, types);

        return method.invoke(service, parameters);


    }
}
