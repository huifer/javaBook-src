package com.huifer.design.singleton;

/**
 * <p>Title : Lazy1 </p>
 * <p>Description : 懒汉式1</p>
 *
 * @author huifer
 * @date 2019-05-16
 */
public class Lazy1 {

    private static Lazy1 lazy1 = null;

    private Lazy1() {
    }

    public static Lazy1 getInstance() {
        if (lazy1 == null) {
            lazy1 = new Lazy1();
        }
        return lazy1;
    }

}
