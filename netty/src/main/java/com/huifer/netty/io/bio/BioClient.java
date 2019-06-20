package com.huifer.netty.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Title : BioClient </p>
 * <p>Description : Bio Client</p>
 *
 * @author huifer
 * @date 2019-06-20
 */
@Slf4j
public class BioClient {

    public static int SERVER_PORT = 10086;

    private static String SERVER_IP = "127.0.0.1";

    public static void send(String exp) {
        send(SERVER_IP, SERVER_PORT, exp);
    }

    private static void send(String serverIp, int serverPort, String exp) {
        log.info("exp : " + exp);
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(serverIp, serverPort);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println( exp);
            log.info("返回结果 " + in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getLocalizedMessage());

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
            }
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
