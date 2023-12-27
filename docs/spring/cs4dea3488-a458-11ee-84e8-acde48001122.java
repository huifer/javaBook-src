package com.huifer.bilibili.visibility;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClazzUtil {
    public static void hh(String className) throws Exception {
        Class clazz = Class.forName(className);
        Object obj = clazz.newInstance();
        // 获取对象属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String upperChar = fieldName.substring(0, 1).toUpperCase();
            String anotherStr = fieldName.substring(1);
            String methodName = "get" + upperChar + anotherStr;
            Method method = clazz.getMethod(methodName);
            Object resultValue = method.invoke(obj);
            // 这里可以编写你的业务代码
            System.out.println(fieldName + ": " + resultValue);
        }
    }
}
