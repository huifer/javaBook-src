package com.huifer.jdk.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>Title : ServerSocketDemo </p>
 * <p>Description : socket server</p>
 *
 * @author huifer
 * @date 2019-06-10
 */
public class ServerSocketDemo {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        BufferedReader bufferedReader = null;

        try {
            serverSocket = new ServerSocket(8080);
            // 等待客户端连接
            Socket accept = serverSocket.accept();
            // inputstream
            bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            // 写出
            System.out.println(bufferedReader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }

            if (serverSocket != null) {
                serverSocket.close();
            }
        }


    }
}
