package com.huifer.jdk.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * <p>Title : ServerSocketDemo01 </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-10
 */
public class ClientSocketDemo01 {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("127.0.0.1", 8080);
        BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter os = new PrintWriter(socket.getOutputStream());
        BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String s = sin.readLine();
        while (!"quit".equals(s)) {
            os.println(s);
            os.flush();
            System.out.println("client " + s);
            System.out.println("server " + is.readLine());
            s = sin.readLine();
        }

        os.close();
        is.close();
        socket.close();

    }


}
