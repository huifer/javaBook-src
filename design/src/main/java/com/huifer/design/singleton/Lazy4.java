package com.huifer.design.singleton;

/**
 * <p>Title : Lazy3 </p>
 * <p>Description : 懒汉式</p>
 *
 * @author huifer
 * @date 2019-05-16
 */
public class Lazy4 {


    private static boolean initialized = false;

    private Lazy4() {
        synchronized (Lazy4.class) {
            if (initialized == false) {
                initialized = !initialized;
            } else {
                throw new RuntimeException("单例初始化异常 ， 私有构造方法被强制使用");
            }
        }
    }

    public static final Lazy4 getInstance() {
        return lazyHolder.LAZY_3;
    }


    private static class lazyHolder {

        private static final Lazy4 LAZY_3 = new Lazy4();
    }
}
