package com.huifer.netty.io.nio.channel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * <p>Title : FileChannelDemo </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-20
 */
public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
        File file = new File("E:\\mck\\javaBook-src\\netty\\src\\main\\resources\\data.data");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        FileChannel fc = fos.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("hello , fileChannel\n".getBytes(StandardCharsets.UTF_8));

        byteBuffer.flip();
        fc.write(byteBuffer);
        byteBuffer.clear();

        byteBuffer.clear();
        fos.close();
        fc.close();


    }
}
