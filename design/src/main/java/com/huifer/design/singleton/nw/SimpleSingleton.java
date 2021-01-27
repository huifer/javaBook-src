package com.huifer.design.singleton.nw;

public class SimpleSingleton {
    private static SimpleSingleton lazy = null;

    private SimpleSingleton() {

    }


    public static synchronized  SimpleSingleton getInstance01() {
        if (lazy == null) {
            synchronized (SimpleSingleton.class) {
                if (lazy == null) {
                    lazy = getLazy();
                }
            }
        }

        return lazy;
    }

    public static synchronized SimpleSingleton getInstance() {
        if (lazy == null) {
            lazy = getLazy();
        }

        return lazy;
    }

    private static SimpleSingleton getLazy() {
        return new SimpleSingleton();
    }
}
