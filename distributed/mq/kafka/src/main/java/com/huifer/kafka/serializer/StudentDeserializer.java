package com.huifer.kafka.serializer;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * <p>Title : StudentDeserializer </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-24
 */
public class StudentDeserializer implements Deserializer<Student> {

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Student deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        if (data.length < 8) {
            throw new SerializationException(
                    "Size of data received by IntegerDeserializer is not 8");
        }
        ByteBuffer wrap = ByteBuffer.wrap(data);
        String name, teacherName;

        int namelen, teacherlen;
        namelen = wrap.getInt();
        byte[] nameBytes = new byte[namelen];
        wrap.get(nameBytes);

        teacherlen = wrap.getInt();
        byte[] teacherBytes = new byte[teacherlen];
        wrap.get(teacherBytes);


        try {
            name = new String(nameBytes, StandardCharsets.UTF_8);
            teacherName = new String(teacherBytes, StandardCharsets.UTF_8);

            return new Student(name, teacherName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {

    }
}
