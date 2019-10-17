package com.huifer.netty.io.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>Title : BioServer </p>
 * <p>Description : BIOserver</p>
 *
 * @author huifer
 * @date 2019-06-20
 */
@Slf4j
public class BioServer {

    private static int PORT = 10086;

    private static ServerSocket serverSocket;


    private BioServer() {
    }

    public static void start() throws IOException {
        start(PORT);
    }

    private synchronized static void start(int port) throws IOException {
        if (serverSocket != null) {
            return;
        }

        try {
            serverSocket = new ServerSocket(port);
            log.info("服务端启动。port =  " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new BioServerHandler(socket)).start();
            }
        } finally {
            if (serverSocket != null) {
                log.info("服务端关闭");
                serverSocket.close();
                serverSocket = null;
            }

        }
    }
}
