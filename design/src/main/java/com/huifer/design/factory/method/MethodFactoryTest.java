package com.huifer.design.factory.method;

import com.huifer.design.factory.Milk;

/**
 * <p>Title : MethodFactoryTest </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-15
 */
public class MethodFactoryTest {

    public static void main(String[] args) {
        MethodFactory factory = new MengNiuFactory();
        Milk milk = factory.createMilk();
        System.out.println(milk.getName());

        MethodFactory factory1 = new YiLiFactory();
        Milk milk1 = factory1.createMilk();
        System.out.println(milk.getName());
    }
}
