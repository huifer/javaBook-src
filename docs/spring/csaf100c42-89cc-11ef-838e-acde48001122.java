package com.huifer.kafka.application.ttl;

/**
 * <p>Title : BytesUtils </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-27
 */
public class BytesUtils {

    public static void main(String[] args) {
        long i = 22L;
        byte[] bytes = long2byte(i);
        long l = byte2long(bytes);
        System.out.println(l);

        int ii = 10;
        byte[] bytes1 = int2byte(ii);
        int i1 = byte2int(bytes1);
        System.out.println(i1);
    }

    public static byte[] long2byte(Long ms) {
        byte[] buffer = new byte[8];
        for (int i = 0; i < 8; i++) {
            int offset = 64 - (i + 1) * 8;
            buffer[i] = (byte) ((ms >> offset) & 0xff);
        }
        return buffer;
    }

    public static long byte2long(byte[] bytes) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value <<= 8;
            value |= (bytes[i] & 0xff);
        }
        return value;
    }

    public static int byte2int(byte[] bytes) {
        return (bytes[0] & 0xff) << 24
                | (bytes[1] & 0xff) << 16
                | (bytes[2] & 0xff) << 8
                | (bytes[3] & 0xff);
    }

    public static byte[] int2byte(int num) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((num >> 24) & 0xff);
        bytes[1] = (byte) ((num >> 16) & 0xff);
        bytes[2] = (byte) ((num >> 8) & 0xff);
        bytes[3] = (byte) (num & 0xff);
        return bytes;
    }

}
