package com.huifer.bigfile.utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * <p>Title : DownloadFileUtils </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-27
 */
public class DownloadFileUtils {

    /**
     * 写文件
     */
    public static void write(String toWrite, InputStream is) throws Exception {

        OutputStream os = new FileOutputStream(toWrite);
        byte[] buf = new byte[1024];
        int len;
        while (-1 != (len = is.read(buf))) {
            os.write(buf, 0, len);
        }
        os.flush();
        os.close();
    }


    public static String generateFileName() {
        return UUID.randomUUID().toString();
    }
}
