package com.huifer.jdk.jdk8.lambda;

@FunctionalInterface
interface MyInterface {
    void test();
}

/**
 * 描述:
 * 函数式接口
 *
 * @author huifer
 * @date 2019-06-12
 */
public class Demo01 {

    public static void main(String[] args) {
        Demo01 demo01 = new Demo01();
        demo01.myInterfaceTest(() -> {
            System.out.println("hello");
        });
    }

    public void myInterfaceTest(MyInterface myInterface) {
        myInterface.test();
    }
}
