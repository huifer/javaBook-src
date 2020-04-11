package com.huifer.design.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.util.concurrent.CountDownLatch;

/**
 * <p>Title : Test </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-16
 */
public class Test {

    public static final int count = 10000;


    public static void main(String[] args) throws Exception {
        // 测试线程安全
//        hungryTest();
//        lazyTest1();
//        lazyTest2();
//        lazyTest3();
//        lazy4Test();

        hungryTest2();

//        enumTest();
//        serializableTest();
    }
    private static void hungryTest2() {
        CountDownLatch latch = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    latch.await();
                    Lazy4 instance = Lazy4.getInstance();
                    System.out.println(System.currentTimeMillis() + " : " + instance);
                } catch (Exception e) {

                }
            }).start();
            latch.countDown();
        }

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
                    // 当count =0 时 释放所有的共享锁， 紧接着开始调用getInstance()
                    latch.await();
                    Lazy2 instance = Lazy2.getInstance();
                    System.out.println(System.currentTimeMillis() + " : " + instance);
                } catch (Exception e) {

                }
            }).start(); // 线程启动

            latch.countDown(); // count --
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
        long l = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(() -> {

                try {
                    latch.await();
                    RegisterMap registerMap = RegisterMap
                            .getInstance("com.huifer.design.singleton.RegisterMap");
                    System.out.println(System.currentTimeMillis() + " : " + registerMap);
                } catch (Exception e) {

                }
            }).start();

            latch.countDown();
        }

        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);

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


    private static void lazy4Test() {
        try {

            Class<Lazy4> lazy4Class = Lazy4.class;

            // 获取私有构造方法 com.huifer.design.singleton.Lazy4.Lazy4
            Constructor<Lazy4> constructor = lazy4Class.getDeclaredConstructor(null);
            // 强制生产
            constructor.setAccessible(true);
            // 构造2次
            Lazy4 lazy4_1 = constructor.newInstance();
            Lazy4 lazy4_2 = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
