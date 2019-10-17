package com.huifer.design.singleton;

/**
 * <p>Title : Hungry </p>
 * <p>Description : 饿汉式</p>
 *
 * @author huifer
 * @date 2019-05-16
 */
public class Hungry {

    /**
     * 提前创建一个实例 且不能修改
     */
    private static final Hungry HUNGRY = new Hungry();

    /**
     * 私有化构造方法
     */
    private Hungry() {
    }

    /**
     * 返回实例
     */
    public static Hungry getInstance() {
        return HUNGRY;
    }

}
