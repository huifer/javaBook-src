package com.huifer.jdk.serializer;

import java.io.IOException;
import java.io.Serializable;

/**
 * <p>Title : TestRun </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-11
 */
public class TestRun {


    public static void main(String[] args) throws IOException {
        User user = new User();
        user.setName("张三");
        MySerializer mySerializer = new MySerializerImpl();

        byte[] serializer = mySerializer.serializer(user);
        User user1 = mySerializer.deSerializer(serializer, User.class);
        System.out.println(user1);


    }


    private static class User implements Serializable {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"name\":\"")
                    .append(name).append('\"');
            sb.append('}');
            return sb.toString();
        }
    }

}

