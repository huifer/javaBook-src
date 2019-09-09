package com.huifer.jdk.jvm;

/**
 * <p>Title : StaticDemo </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-05
 */
public class StaticDemo {

    public static final StaticDemo STATIC_DEMO = new StaticDemo(); // static 修饰
    private static final Boolean LIVING = true; // 这段代码的加载事件太靠后了
    private final Boolean alive = LIVING;

    private StaticDemo() {
    }

    public static void main(String[] args) {
        System.out.println(STATIC_DEMO.lives() ? "y" : "n");

    }

    public final Boolean lives() {
        return alive;
    }
}
