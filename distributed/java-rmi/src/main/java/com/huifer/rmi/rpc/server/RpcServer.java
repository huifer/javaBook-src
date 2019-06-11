package com.huifer.rmi.rpc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Title : RpcServer </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-11
 */
public class RpcServer {


    private final ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 发布一个service
     *
     * @param service service {@link com.huifer.rmi.rpc.HelloService}
     * @param port port
     */
    public void publisher(final Object service, int port) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(socket, service));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }

}
