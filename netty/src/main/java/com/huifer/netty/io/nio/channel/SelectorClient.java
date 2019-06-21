package com.huifer.netty.io.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * <p>Title : SelectorClient </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-21
 */
public class SelectorClient {

    public static void main(String[] args) {
        try {
            SocketChannel channel = SocketChannel.open();
            channel.connect(new InetSocketAddress("127.0.0.1", 9999));
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            writeBuffer.put("hello-client".getBytes());
            writeBuffer.flip();
            while (true) {
                writeBuffer.rewind();
                channel.write(writeBuffer);
                readBuffer.clear();
                channel.read(readBuffer);
                String s = new String(readBuffer.array());
                System.out.println(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
