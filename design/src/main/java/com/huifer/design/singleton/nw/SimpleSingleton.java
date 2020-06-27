package com.huifer.design.singleton.nw;

public class SimpleSingleton {
    private static SimpleSingleton lazy = null;

    private SimpleSingleton() {

    }


    public synchronized static SimpleSingleton getInstance01() {
        if (lazy == null) {
            synchronized (SimpleSingleton.class) {
                if (lazy == null) {
                    lazy = new SimpleSingleton();
                }
            }
        }

        return lazy;
    }

    public synchronized static SimpleSingleton getInstance() {
        if (lazy == null) {
            lazy = new SimpleSingleton();
        }

        return lazy;
    }
}
