package com.huifer.design.singleton;

/**
 * <p>Title : Lazy3 </p>
 * <p>Description : 懒汉式</p>
 *
 * @author huifer
 * @date 2019-05-16
 */
public class Lazy3 {


    private Lazy3() {

    }


    public static final Lazy3 getInstance() {
        return lazyHolder.LAZY_3;
    }


    private static class lazyHolder {

        // 内部类会率先初始化
        private static final Lazy3 LAZY_3 = new Lazy3();
    }
}
