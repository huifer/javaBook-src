package com.huifer.jdk.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <p>Title : MySerializerImpl </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-11
 */
public class MySerializerImpl implements MySerializer {

    @Override
    public <T> byte[] serializer(T obj) throws IOException {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            return bos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                oos.close();
            }
            bos.close();
        }
        return null;
    }

    @Override
    public <T> T deSerializer(byte[] data, Class<T> clazz) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(bis);
            return (T) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                ois.close();
            }
            bis.close();
        }

        return null;
    }
}
