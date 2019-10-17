package com.huifer.design.factory.method;

import com.huifer.design.factory.Milk;
import com.huifer.design.factory.YiLi;

/**
 * <p>Title : MengNiuFactory </p>
 * <p>Description : 伊利的生产工厂 ，该工厂只生产伊利</p>
 *
 * @author huifer
 * @date 2019-05-15
 */
public class YiLiFactory implements MethodFactory {

    @Override
    public Milk createMilk() {
        System.out.println("蒙牛材料清单 ： 牛奶 200 克");
        return new YiLi();
    }

}
