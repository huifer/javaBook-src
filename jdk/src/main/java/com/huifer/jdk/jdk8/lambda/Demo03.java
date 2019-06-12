package com.huifer.jdk.jdk8.lambda;

/**
 * 描述:
 * lambda是对象
 *
 * @author huifer
 * @date 2019-06-12
 */
public class Demo03 {
    public static void main(String[] args) {
        Interface01 i1 = () -> {
        };
        Interface02 i2 = () -> {
        };


        System.out.println(i1.getClass().getInterfaces()[0]);
        System.out.println(i2.getClass().getInterfaces()[0]);

    }
}

@FunctionalInterface
interface Interface01 {
    void demo();
}


@FunctionalInterface
interface Interface02 {
    void demo();
}
