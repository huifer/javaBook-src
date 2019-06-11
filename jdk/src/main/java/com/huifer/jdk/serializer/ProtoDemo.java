package com.huifer.jdk.serializer;

import com.google.protobuf.ByteString;
import com.huifer.jdk.serializer.proto.UserProto;

/**
 * <p>Title : ProtoDemo </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-11
 */
public class ProtoDemo {

    public static void main(String[] args) throws Exception {
        UserProto.MyUser myUser = UserProto.MyUser.newBuilder().setAge(10).setName("zhangsan").build();

        ByteString bytes = myUser.toByteString();

        System.out.println(bytes);

        UserProto.MyUser myUser1 = UserProto.MyUser.parseFrom(bytes);
        System.out.println(myUser1);
    }

}
