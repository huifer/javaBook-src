package com.huifer.netty.io.bio;

import java.io.IOException;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Title : BioTest </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-20
 */
@Slf4j
public class BioTest {

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                BioServer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000);

        Random random = new Random();

        new Thread(() -> {
            while (true) {
                String exp = String.valueOf(random.nextInt((int) System.currentTimeMillis()));
                BioClient.send(exp);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
