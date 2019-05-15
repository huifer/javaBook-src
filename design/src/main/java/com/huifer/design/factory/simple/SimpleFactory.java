package com.huifer.design.factory.simple;

import com.huifer.design.factory.MengNiu;
import com.huifer.design.factory.Milk;
import com.huifer.design.factory.YiLi;

/**
 * <p>Title : SimpleFactory </p>
 * <p>Description : 牛奶的简单工厂</p>
 *
 * @author huifer
 * @date 2019-05-15
 */
public class SimpleFactory {

    public Milk getMilk(String name) {
        if ("蒙牛".equals(name)) {
            return new MengNiu();
        } else if ("伊利".equals(name)) {
            return new YiLi();
        } else {
            return null;
        }
    }

}
