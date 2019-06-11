package com.huifer.jdk.serializer;

import java.io.IOException;

/**
 * <p>Title : MySerializer </p>
 * <p>Description : 序列化</p>
 *
 * @author huifer
 * @date 2019-06-11
 */
public interface MySerializer {

    /**
     * 序列化
     */
    <T> byte[] serializer(T obj) throws IOException;

    /**
     * 反序列化
     */
    <T> T deSerializer(byte[] data, Class<T> clazz) throws IOException;

}
