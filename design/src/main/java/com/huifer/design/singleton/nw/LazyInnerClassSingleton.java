package com.huifer.design.singleton.nw;

public class LazyInnerClassSingleton {
    private LazyInnerClassSingleton() {
        throw new RuntimeException("ex");
    }

    public static LazyInnerClassSingleton getInstance() {
        return LazyObj.lazy;
    }

    private static class LazyObj {
        public static final LazyInnerClassSingleton lazy = new LazyInnerClassSingleton();

    }
}
