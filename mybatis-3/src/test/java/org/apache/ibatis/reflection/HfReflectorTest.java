package org.apache.ibatis.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;


class HfReflectorTest {

    @Test
    void getDefaultConstructorTest() throws Exception {
        Reflector reflector = new Reflector(People.class);
        // 获取空参构造方法
        Constructor<?> defaultConstructor = reflector.getDefaultConstructor();
        People o = (People) defaultConstructor.newInstance();
        o.setName("hhh");
        System.out.println(o);

        String[] getablePropertyNames = reflector.getGetablePropertyNames();
        for (String getablePropertyName : getablePropertyNames) {
            System.out.println(getablePropertyName);
        }

    }

    @Test
    void testExtend() {
        Reflector reflector = new Reflector(Man.class);

    }


}