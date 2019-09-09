package com.huifer.design.factory.abs;

import com.huifer.design.factory.Milk;

/**
 * <p>Title : AbstractFactory </p>
 * <p>Description : 抽象工厂</p>
 *
 * @author huifer
 * @date 2019-05-15
 */
public abstract class AbstractFactory {

    public void hello() {
        System.out.println("hello factory");
    }

    /**
     * 蒙牛
     */
    public abstract Milk getMengNiu();

    /**
     * 伊利
     *
     * @return
     */
    public abstract Milk getYiLi();

}
