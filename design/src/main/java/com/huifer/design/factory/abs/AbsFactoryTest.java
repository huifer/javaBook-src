package com.huifer.design.factory.abs;

import com.huifer.design.factory.Milk;

/**
 * <p>Title : AbsFactoryTest </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-15
 */
public class AbsFactoryTest {

    public static void main(String[] args) {
        MilkFactory milkFactory = new MilkFactory();
        Milk mengNiu = milkFactory.getMengNiu();
        System.out.println(mengNiu.getName());

    }

}
