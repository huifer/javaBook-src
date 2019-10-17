package com.huifer.design.factory.simple;

import com.huifer.design.factory.Milk;

/**
 * <p>Title : SimpleFactoryTest </p>
 * <p>Description : 简单工厂测试类</p>
 *
 * @author huifer
 * @date 2019-05-15
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        Milk mn = simpleFactory.getMilk("蒙牛");
        Milk yili = simpleFactory.getMilk("伊利");

        System.out.println(mn.getName());
        System.out.println(yili.getName());
    }

}
