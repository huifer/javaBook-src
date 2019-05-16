package com.huifer.design.singleton;

/**
 * <p>Title : Lazy1 </p>
 * <p>Description : 懒汉式1</p>
 *
 * @author huifer
 * @date 2019-05-16
 */
public class Lazy1 {

    // 静态代码块
    private static Lazy1 lazy1 = null;

    private Lazy1() {
    }

    public static Lazy1 getInstance() {
        if (lazy1 == null) {
            // 先判断是否存在该实例 ，如果不存在则创建实例
            lazy1 = new Lazy1();
        }
        // 实例存在则返回该实例
        return lazy1;
    }

}
