package com.huifer.netty.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Title : BioServerHandler </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-20
 */
@Slf4j
public class BioServerHandler implements Runnable {

    private Socket socket;

    public BioServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String result;
            String exp;
            while (true) {
                if ((exp = in.readLine()) == null) {
                    break;
                }
                log.info("服务端收到信息" + exp);
                result = "服务端处理结果:" + exp;

                out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if (out != null) {
                out.close();
                out = null;
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;

            }

        }

    }
}
