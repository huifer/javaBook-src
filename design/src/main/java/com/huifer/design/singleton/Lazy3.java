package com.huifer.design.singleton;

/**
 * <p>Title : Lazy3 </p>
 * <p>Description : 懒汉式</p>
 *
 * @author huifer
 * @date 2019-05-16
 */
public class Lazy3 {

    /**
     * 静态内部类形式的懒汉式单例，解决了饿汉式的内存使用问题，解决了同步锁的性能问题(synchronized)
     * 特点：外部类调用时内部类才会被调用，原因 内部类一定在方法调用前初始化
     */


    private Lazy3() {

    }

    /**
     * static 保证单例的空间共享出去
     * final 保证不被重写
     *
     * @return
     */
    public static final Lazy3 getInstance() {
        // 方法执行前，先执行lazyHolder的加载
        return lazyHolder.LAZY_3;
    }


    private static class lazyHolder {

        // 内部类会率先初始化
        private static final Lazy3 LAZY_3 = new Lazy3();
    }
}
