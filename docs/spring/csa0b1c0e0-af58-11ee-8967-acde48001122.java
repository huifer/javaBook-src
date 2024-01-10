package com.huifer.jdk.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>Title : ServerSocketDemo01 </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-10
 */
public class ServerSocketDemo01 {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = null;

        serverSocket = new ServerSocket(8080);
        Socket accept = serverSocket.accept();

        BufferedReader is = new BufferedReader(new InputStreamReader(accept.getInputStream()));

        PrintWriter os = new PrintWriter(accept.getOutputStream());
        BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));

        // 输出客户端信息
        System.out.println("client : " + is.readLine());
        String s = sin.readLine();
        while (!"quit".equals(s)) {
            os.println(s);
            os.flush();
            System.out.println("server： " + s);
            System.out.println("client: " + is.readLine());
            s = sin.readLine();
        }
        os.close();
        is.close();
        serverSocket.close();


    }


}
