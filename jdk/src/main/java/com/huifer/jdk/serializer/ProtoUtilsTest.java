package com.huifer.jdk.serializer;

/**
 * <p>Title : ProtoUtilsTest </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-24
 */
public class ProtoUtilsTest {

    public static void main(String[] args) {
        Student student = new Student("张三", 22);
        byte[] serializer = ProtoBufUtil.serializer(student);
        Student deserializer = ProtoBufUtil.deserializer(serializer, Student.class);
        System.out.println(serializer);
        System.out.println(deserializer);
    }

}
