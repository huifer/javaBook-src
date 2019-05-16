package com.huifer.design.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.CountDownLatch;

/**
 * <p>Title : Test </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-16
 */
public class Test {

    public static final int count = 10;


    public static void main(String[] args) throws Exception {
        // 测试线程安全
//        hungryTest();
//        lazyTest1();
//        lazyTest2();
//        lazyTest3();

        registerTest();

//        enumTest();
//        serializableTest();
    }

    /**
     * 饿汉式线程安全测试
     */
    private static void hungryTest() {
        CountDownLatch latch = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    latch.await();
                    Hungry instance = Hungry.getInstance();
                    System.out.println(System.currentTimeMillis() + " : " + instance);
                } catch (Exception e) {

                }
            }).start();
            latch.countDown();
        }

    }

    /**
     * 懒汉式线程安全测试
     */
    private static void lazyTest1() {
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(() -> {

                try {
                    latch.await();
                    Lazy1 instance = Lazy1.getInstance();
                    System.out.println(System.currentTimeMillis() + " : " + instance);
                } catch (Exception e) {

                }
            }).start();

            latch.countDown();
        }
    }

    private static void lazyTest2() {
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(() -> {

                try {
                    latch.await();
                    Lazy2 instance = Lazy2.getInstance();
                    System.out.println(System.currentTimeMillis() + " : " + instance);
                } catch (Exception e) {

                }
            }).start();

            latch.countDown();
        }
    }


    private static void lazyTest3() {
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(() -> {

                try {
                    latch.await();
                    Lazy3 instance = Lazy3.getInstance();
                    System.out.println(System.currentTimeMillis() + " : " + instance);
                } catch (Exception e) {

                }
            }).start();

            latch.countDown();
        }
    }

    private static void registerTest() {
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(() -> {

                try {
                    latch.await();
                    RegisterMap registerMap = RegisterMap.getInstance("registerMap");

                    System.out.println(System.currentTimeMillis() + " : " + registerMap);
                } catch (Exception e) {

                }
            }).start();

            latch.countDown();
        }

    }


    private static void enumTest() {
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(() -> {

                try {
                    latch.await();
                    Object o = EnumSing.INSTANCE.getInstance();
                    System.out.println(System.currentTimeMillis() + " : " + o);
                } catch (Exception e) {

                }
            }).start();

            latch.countDown();
        }

    }


    private static void serializableTest() {
        SerializableSign s1 = null;
        SerializableSign s2 = SerializableSign.getInstance();
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {

            // 测试序列化是否单例
            // 写入本地
            fos = new FileOutputStream("SerializableSign.obj");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(s2);
            oos.flush();
            oos.close();
            // 从本地读取
            fis = new FileInputStream("SerializableSign.obj");
            ois = new ObjectInputStream(fis);
            s1 = (SerializableSign) ois.readObject();
            ois.close();

            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s1 == s2);
        } catch (Exception e) {

        }


    }

}
