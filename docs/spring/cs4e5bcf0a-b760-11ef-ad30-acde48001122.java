package com.huifer.design.factory.method;

import com.huifer.design.factory.MengNiu;
import com.huifer.design.factory.Milk;

/**
 * <p>Title : MengNiuFactory </p>
 * <p>Description : 蒙牛的生产工厂 ，该工厂只生产蒙牛</p>
 *
 * @author huifer
 * @date 2019-05-15
 */
public class MengNiuFactory implements MethodFactory {

    @Override
    public Milk createMilk() {
        System.out.println("蒙牛材料清单 ： 牛奶 100 克");
        return new MengNiu();
    }

}
