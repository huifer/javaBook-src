package com.huifer.utils.factory;

import com.alibaba.fastjson.JSON;
import com.huifer.utils.entity.demo.EntityJsonDemo;
import com.huifer.utils.entity.demo.HelloJson;
import com.huifer.utils.entity.demo.JsonAnn;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 转换工厂
 */
public class TransformFactory {
    public static final String json = "{\n" +
            "\t\"name\": \"zs\",\n" +
            "\t\"age\": \"1\"\n" +
            "}";

    public static <T> T hh(Class c, String s) {

        if (c.equals(HelloJson.class)) {
            HelloJson helloJson = new HelloJson();
            return (T) helloJson.transform(s);
        }
        return null;
    }

    public static <T> T ins(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        return tClass.newInstance();
    }

    public static <T> Class<T> st(Class<?> s) {
        return (Class<T>) s;
    }

    public static Class<?> getClazz(String s) throws ClassNotFoundException {
        return Class.forName(s);
    }

    public static Object ov(String json, String clazz) throws ClassNotFoundException {

        Object o = JSON.parseObject(json, st(getClazz(clazz)));
        return o;
    }


    public static String getJsonAnnValue(JsonAnn jsonAnn) {
        return jsonAnn.clazz().getName();
    }


    public static void main(String[] args) throws Exception {
        EntityJsonDemo entityJsonDemo = new EntityJsonDemo();
        entityJsonDemo.setJsonStr(json);

        Field[] declaredFields = entityJsonDemo.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {

            declaredField.setAccessible(true);

            Object value = declaredField.get(entityJsonDemo);


            Annotation[] annotations = declaredField.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof JsonAnn) {
                    String jsonAnnValue = getJsonAnnValue((JsonAnn) annotation);
                    Object ov = ov((String) value, jsonAnnValue);
                    if (ov instanceof HelloJson) {
                        // 类型为helloJson时
                        HelloJson helloJson = (HelloJson) ov;
                        System.out.println(helloJson.getAge());
                        System.out.println(helloJson.getName());
                    }
                }
            }
        }


    }


}
