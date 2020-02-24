package com.huifer.bilibili.inteface;

interface IHello {
    void hello();
}

class HelloImpl implements IHello {
    public void hello() {
        System.out.println("hello");
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        HelloImpl hello = new HelloImpl();
        hello.hello();
    }
}