package com.huifer.design.singleton.nw;


import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class LazyInnerClassSingletonTest  {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<LazyInnerClassSingleton> clazz = LazyInnerClassSingleton.class;
        Constructor<LazyInnerClassSingleton> declaredConstructor = clazz.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyInnerClassSingleton lazyInnerClassSingleton = declaredConstructor.newInstance(null);
        // 输出地址
        System.out.println(lazyInnerClassSingleton);
        // 输出地址
        System.out.println(LazyInnerClassSingleton.getInstance());
    }

}