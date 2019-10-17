package com.huifer.design.singleton;

/**
 * <p>Title : Lazy1 </p>
 * <p>Description : 懒汉式1</p>
 *
 * @author huifer
 * @date 2019-05-16
 */
public class Lazy2 {

    private static Lazy2 lazy2 = null;

    private Lazy2() {
    }

    public static synchronized Lazy2 getInstance() {
        if (lazy2 == null) {
            lazy2 = new Lazy2();
        }
        return lazy2;
    }

}
