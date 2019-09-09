package com.huifer.zk.rpcclient;

import com.huifer.zk.rpcserver.ZkRpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * <p>Title : ZkTransport </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-13
 */
public class ZkTransport {

    private String serviceAddress;

    public ZkTransport(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }


    private Socket newSocket() {
        Socket socket = null;
        String[] split = serviceAddress.split(":");
        try {
            socket = new Socket(split[0], Integer.parseInt(split[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }


    public Object send(ZkRpcRequest request) {
        System.out.println("ZkTransport 开始发送");
        Socket socket = null;
        try {
            socket = newSocket();
            ObjectOutputStream oos = new ObjectOutputStream(
                    socket.getOutputStream());

            oos.writeObject(request);
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(
                    socket.getInputStream()
            );
            Object o = ois.readObject();
            ois.close();
            oos.close();
            return o;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
