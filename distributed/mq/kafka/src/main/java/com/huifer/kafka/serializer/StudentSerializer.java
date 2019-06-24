package com.huifer.kafka.serializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.kafka.common.serialization.Serializer;

/**
 * <p>Title : StudentSerializer </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-24
 */
public class StudentSerializer implements Serializer<Student> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Student data) {
        if (data == null) {
            return null;
        }
        byte[] name,  teacherName;
        try {
            if (data.getName() != null) {
                name = data.getName().getBytes(StandardCharsets.UTF_8);
            } else {
                name = new byte[0];
            }

            if (data.getTeacherName() != null) {
                teacherName = data.getTeacherName().getBytes(StandardCharsets.UTF_8);
            } else {
                teacherName = new byte[0];
            }


            ByteBuffer buffer = ByteBuffer
                    .allocate(4 + 4 + name.length + teacherName.length );
            buffer.putInt(name.length);
            buffer.put(name);
            buffer.putInt(teacherName.length);
            buffer.put(teacherName);
            return buffer.array();


        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public void close() {

    }
}
