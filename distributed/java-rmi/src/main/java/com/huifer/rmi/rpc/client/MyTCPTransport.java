package com.huifer.rmi.rpc.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * <p>Title : MyTCPTransport </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-11
 */
public class MyTCPTransport {

    private String host;
    private int port;

    public MyTCPTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 创建一个socket
     * @return
     */
    private Socket newSocket() {
        Socket socket;
        try {
            socket = new Socket(this.host, this.port);
            return socket;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 远程信息交互
     * @param rpcRequest
     * @return
     * @throws IOException
     */
    public Object send(RpcRequest rpcRequest) throws IOException {
        Socket socket = null;
        try {
            socket = newSocket();
            ObjectOutputStream oos = new ObjectOutputStream(
                    socket.getOutputStream());

            oos.writeObject(rpcRequest);
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
                socket.close();
            }
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
