package com.huifer.design.factory.abs;

import com.huifer.design.factory.Milk;
import com.huifer.design.factory.method.MengNiuFactory;
import com.huifer.design.factory.method.YiLiFactory;

/**
 * <p>Title : MilkFactory </p>
 * <p>Description : 牛奶工厂</p>
 *
 * @author huifer
 * @date 2019-05-15
 */
public class MilkFactory extends AbstractFactory {

    @Override
    public void hello() {
        super.hello();
    }

    @Override
    public Milk getMengNiu() {
        return new MengNiuFactory().createMilk();
    }

    @Override
    public Milk getYiLi() {
        return new YiLiFactory().createMilk();
    }

}
