package com.huifer.jdk.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * <p>Title : ClientSocketDemo </p>
 * <p>Description : ClientSocketDemo</p>
 *
 * @author huifer
 * @date 2019-06-10
 */
public class ClientSocketDemo {

    public static void main(String[] args) throws IOException {
        Socket socket = null;

        try {
            socket = new Socket("127.0.0.1", 8080);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("hello");
        } catch (Exception e) {

        } finally {

            if (socket != null) {
                socket.close();
            }
        }


    }
}
